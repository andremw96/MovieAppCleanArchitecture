package com.andreamw96.core.domain.usecase

import com.andreamw96.core.domain.model.Movie
import com.andreamw96.core.domain.repository.IMovieRepository

class MovieInteractor constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieRepository.setFavoriteMovie(movie, state)
    }
}