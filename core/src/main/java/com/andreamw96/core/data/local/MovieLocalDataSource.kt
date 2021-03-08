package com.andreamw96.core.data.local

import com.andreamw96.core.data.local.entity.MovieEntity
import com.andreamw96.core.data.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovie() = movieDao.getAllMovie()

    fun getAllFavorite() = movieDao.getFavoriteMovie()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateFavoriteMovie(movieEntity)
    }
}