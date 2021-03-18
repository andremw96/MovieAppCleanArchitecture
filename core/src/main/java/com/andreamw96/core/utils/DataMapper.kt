package com.andreamw96.core.utils

import com.andreamw96.core.data.local.entity.MovieEntity
import com.andreamw96.core.data.remote.response.MovieResponse
import com.andreamw96.core.domain.model.Movie

object DataMapper {
    fun List<MovieEntity>.entityToDomain(): List<Movie> =
        this.map {
            Movie(
                it.id,
                it.backdropPath,
                it.overview,
                it.releaseDate,
                it.title,
                it.voteAverage,
                it.isFavorite
            )
        }

    fun List<MovieResponse>.responseToEntity(): List<MovieEntity> =
        this.map {
            MovieEntity(
                it.backdropPath,
                it.id,
                it.overview,
                it.releaseDate,
                it.title,
                it.voteAverage,
                false
            )
        }

    fun Movie.domainToEntity(): MovieEntity =
        MovieEntity(
            backdropPath,
            id,
            overview,
            releaseDate,
            title,
            voteAverage,
            isFavorite
        )
}