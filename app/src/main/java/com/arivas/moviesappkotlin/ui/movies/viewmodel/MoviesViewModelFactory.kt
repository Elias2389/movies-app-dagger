package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable

class MoviesViewModelFactory(private val moviesObservable: MoviesObservable): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesObservable) as T
    }
}