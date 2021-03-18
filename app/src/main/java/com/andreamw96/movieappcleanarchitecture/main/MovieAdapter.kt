package com.andreamw96.movieappcleanarchitecture.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.core.data.remote.IMAGE_BASE_URL
import com.andreamw96.core.domain.model.Movie
import com.andreamw96.movieappcleanarchitecture.R
import com.andreamw96.movieappcleanarchitecture.databinding.RvMovieItemBinding
import com.bumptech.glide.Glide

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    var onItemMovieClick: ((Movie) -> Unit)? = null

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_movie_item, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = currentList[position]
        holder.bind(data)
    }

    inner class MovieViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val binding = RvMovieItemBinding.bind(v)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("$IMAGE_BASE_URL/${movie.backdropPath}")
                    .into(imgMovie)
                txtMovieTitle.text = movie.title
                txtDate.text = movie.releaseDate
                ratingBar.progress = movie.voteAverage.toInt()
                rootItemMovie.setOnClickListener {
                    onItemMovieClick?.invoke(currentList[adapterPosition])
                }
            }
        }
    }
}