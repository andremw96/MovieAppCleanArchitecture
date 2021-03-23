package com.andreamw96.core.di

import com.andreamw96.core.data.MovieRepository
import com.andreamw96.core.data.local.MovieLocalDataSource
import com.andreamw96.core.data.remote.MovieRemoteDataSource
import com.andreamw96.core.domain.repository.IMovieRepository
import com.andreamw96.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module{
    single { MovieLocalDataSource(get()) }
    single { MovieRemoteDataSource(get()) }
    single { AppExecutors() }

    single<IMovieRepository>{
        MovieRepository(get(), get(), get())
    }
}