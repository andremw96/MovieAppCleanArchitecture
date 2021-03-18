package com.andreamw96.movieappcleanarchitecture.detailmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.movieappcleanarchitecture.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

    }
}