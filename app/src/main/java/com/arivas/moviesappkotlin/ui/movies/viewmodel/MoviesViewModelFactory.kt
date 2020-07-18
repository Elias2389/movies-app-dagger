package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory<T : ViewModel> @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val viewModelProvider: Provider<T>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //return MoviesViewModel(moviesRepository) as T
        return viewModelProvider.get() as T
    }
}