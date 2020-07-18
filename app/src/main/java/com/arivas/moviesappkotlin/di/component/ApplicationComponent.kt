package com.arivas.moviesappkotlin.di.component

import com.arivas.moviesappkotlin.di.module.ApplicationModule
import com.arivas.moviesappkotlin.di.module.ViewModelModule
import com.arivas.moviesappkotlin.ui.movies.view.MoviesActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun inject(moviesActivity: MoviesActivity)
}