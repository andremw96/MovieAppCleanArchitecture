package com.andreamw96.movieappcleanarchitecture

import android.app.Application
import com.andreamw96.core.di.databaseModule
import com.andreamw96.core.di.networkModule
import com.andreamw96.core.di.repositoryModule
import com.andreamw96.movieappcleanarchitecture.di.useCaseModule
import com.andreamw96.movieappcleanarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}