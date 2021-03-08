package com.andreamw96.core.domain.usecase

import android.content.res.Resources
import com.andreamw96.core.data.Resource
import com.andreamw96.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies() : Flow<Resource<List<Movie>>>
    fun getFavoriteMovies() : Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}