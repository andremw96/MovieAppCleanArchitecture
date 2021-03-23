package com.andreamw96.movieappcleanarchitecture.detailmovie

import androidx.lifecycle.ViewModel
import com.andreamw96.core.domain.model.Movie
import com.andreamw96.core.domain.usecase.MovieUseCase

class DetailMovieViewModel constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun updateFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}