package com.andreamw96.core.di

import androidx.room.Room
import com.andreamw96.core.data.local.room.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    factory {
        get<MovieDatabase>().movieDao()
    }
}