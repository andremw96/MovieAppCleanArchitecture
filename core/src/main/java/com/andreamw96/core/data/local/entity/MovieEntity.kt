package com.andreamw96.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity(
    var backdropPath: String,
    @PrimaryKey
    var id: Int = -1,
    var overview: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false
)