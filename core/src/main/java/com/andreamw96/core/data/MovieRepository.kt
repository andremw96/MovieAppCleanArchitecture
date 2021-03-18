package com.andreamw96.core.data

import com.andreamw96.core.data.local.MovieLocalDataSource
import com.andreamw96.core.data.remote.MovieRemoteDataSource
import com.andreamw96.core.data.remote.network.ApiResponse
import com.andreamw96.core.data.remote.response.MovieResponse
import com.andreamw96.core.domain.model.Movie
import com.andreamw96.core.domain.repository.IMovieRepository
import com.andreamw96.core.utils.AppExecutors
import com.andreamw96.core.utils.DataMapper.domainToEntity
import com.andreamw96.core.utils.DataMapper.entityToDomain
import com.andreamw96.core.utils.DataMapper.responseToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    it.entityToDomain()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data != null && data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = data.responseToEntity()
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getAllFavorite().map {
            it.entityToDomain()
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = movie.domainToEntity()
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

}