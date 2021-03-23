package com.andreamw96.movieappcleanarchitecture.main.moviefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andreamw96.core.domain.usecase.MovieUseCase

class MainViewModel constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movieList = movieUseCase.getAllMovies().asLiveData()
}