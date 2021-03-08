package com.andreamw96.core.data.local.room

import androidx.room.*
import com.andreamw96.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies_table where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

}