package com.andreamw96.movieappcleanarchitecture.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andreamw96.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect

class MainViewModel @ViewModelInject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movieList = movieUseCase.getAllMovies().asLiveData()
}