package com.andreamw96.movieappcleanarchitecture.detailmovie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.andreamw96.core.data.remote.IMAGE_BASE_URL
import com.andreamw96.core.domain.model.Movie
import com.andreamw96.movieappcleanarchitecture.R
import com.andreamw96.movieappcleanarchitecture.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailMovieViewModel by viewModels()

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        showDetailMovieFromIntent()
    }

    private fun showDetailMovieFromIntent() {
        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        detailMovie?.let { movie ->
            supportActionBar?.title = movie.title
            Glide.with(this)
                .load("$IMAGE_BASE_URL/${movie.backdropPath}")
                .into(binding.ivDetailImage)

            var statusFavorite = movie.isFavorite
            favoriteState(statusFavorite)
            with(binding.content) {
                detailTitleMovie.text = movie.title
                detailRatingMovie.text = movie.voteAverage.toString()
                detailDateMovie.text = movie.releaseDate
                detailDescriptionMovie.text = movie.overview
            }

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.updateFavoriteMovie(movie, statusFavorite)
                favoriteState(statusFavorite)
            }
        }
    }

    private fun favoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }
}