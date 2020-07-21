package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.ui.movies.paging.MoviesDataSource
import com.arivas.moviesappkotlin.ui.movies.paging.MoviesDataSourceFactory
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getPopularMovies(): MutableLiveData<MoviesDataSource> {
        val moviesDataSourceFactory = MoviesDataSourceFactory()
        return moviesDataSourceFactory.movies
    }
}