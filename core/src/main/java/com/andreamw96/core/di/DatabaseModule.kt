package com.andreamw96.core.di

import androidx.room.Room
import com.andreamw96.core.data.local.room.MovieDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        val passphrase = SQLiteDatabase.getBytes("andre".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    factory {
        get<MovieDatabase>().movieDao()
    }
}