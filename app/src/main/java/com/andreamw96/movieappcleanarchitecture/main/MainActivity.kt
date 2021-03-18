package com.andreamw96.movieappcleanarchitecture.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.core.data.Resource
import com.andreamw96.movieappcleanarchitecture.databinding.ActivityMainBinding
import com.andreamw96.movieappcleanarchitecture.detailmovie.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        mainViewModel.movieList.observe(this, { data ->
            if (data != null) {
                when(data) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(data.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        movieAdapter.onItemMovieClick = { selectedMovie ->
            startActivity(
                Intent(this, DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.EXTRA_DATA, selectedMovie)
            )
        }
    }
}