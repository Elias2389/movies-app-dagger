package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        return moviesRepository.getPopularMovies()
    }
}