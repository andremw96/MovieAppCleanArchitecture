package com.andreamw96.core.domain.repository

import com.andreamw96.core.data.Resource
import com.andreamw96.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovies() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}