package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(private val moviesObservable: MoviesObservable): ViewModel() {

    fun popularMovies() {
        moviesObservable.popularMovies()
    }

    fun getPopularMovies(): MutableLiveData<MoviesResponse> {
        return moviesObservable.getPopularMovies()
    }
}