package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val moviesRepository: MoviesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesRepository) as T
    }
}