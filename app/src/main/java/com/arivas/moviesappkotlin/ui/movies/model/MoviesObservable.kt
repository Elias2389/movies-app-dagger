package com.arivas.moviesappkotlin.ui.movies.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository

class MoviesObservable(private val moviesRepository: MoviesRepository): BaseObservable() {

    fun popularMovies() {
        moviesRepository.popularMovies()
    }

    fun getPopularMovies(): MutableLiveData<MoviesResponse> {
        return moviesRepository.getPopularMovies()
    }
}