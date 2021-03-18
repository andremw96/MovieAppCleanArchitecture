package com.andreamw96.movieappcleanarchitecture.main.moviefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andreamw96.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movieList = movieUseCase.getAllMovies().asLiveData()
}