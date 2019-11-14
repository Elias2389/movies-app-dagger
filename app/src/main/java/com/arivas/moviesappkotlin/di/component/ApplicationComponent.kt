package com.arivas.moviesappkotlin.di.component

import com.arivas.moviesappkotlin.di.module.ApplicationModule
import com.arivas.moviesappkotlin.di.module.NetworkModule
import com.arivas.moviesappkotlin.ui.movies.interactor.MoviesInteractor
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenter
import com.arivas.moviesappkotlin.ui.movies.view.MoviesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(moviesActivity: MoviesActivity)
    fun inject(moviesInteractor: MoviesInteractor)
    fun inject(moviesPresenter: MoviesPresenter)
}