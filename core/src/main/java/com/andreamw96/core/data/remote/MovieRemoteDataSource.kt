package com.andreamw96.core.data.remote

import com.andreamw96.core.data.Resource
import com.andreamw96.core.data.remote.network.ApiResponse
import com.andreamw96.core.data.remote.network.MovieApi
import com.andreamw96.core.data.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieApi.getMovies(API_KEY, "en-US")
                val dataMovies = response.results
                if (dataMovies.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}