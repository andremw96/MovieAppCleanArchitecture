package com.andreamw96.core.data.remote.network

import com.andreamw96.core.data.remote.response.MovieResponseHeader
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieResponseHeader
}