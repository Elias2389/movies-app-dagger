package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(private val moviesObservable: MoviesObservable): ViewModel() {

    fun popularMovies() {
        moviesObservable.popularMovies()
    }

    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        return moviesObservable.getPopularMovies()
    }
}