package com.andreamw96.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andreamw96.core.domain.usecase.MovieUseCase

class FavoriteViewModel constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovieList = movieUseCase.getFavoriteMovies().asLiveData()
}