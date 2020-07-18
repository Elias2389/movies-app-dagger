package com.arivas.moviesappkotlin.di.module

import androidx.lifecycle.ViewModel
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun providesMainViewModel(moviesViewModel: MoviesViewModel): ViewModel
}