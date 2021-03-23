package com.andreamw96.movieappcleanarchitecture.di


import com.andreamw96.core.domain.usecase.MovieInteractor
import com.andreamw96.core.domain.usecase.MovieUseCase
import com.andreamw96.movieappcleanarchitecture.detailmovie.DetailMovieViewModel
import com.andreamw96.movieappcleanarchitecture.main.moviefragment.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {
        MovieInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}
