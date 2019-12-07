package com.arivas.moviesappkotlin.ui.movies.repository

import androidx.lifecycle.MutableLiveData
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import io.reactivex.Observable

interface MoviesRepository {
    /**
     * Get popular movies
     */
    fun popularMovies()

    /**
     * Get popular movies
     */
    fun getPopularMovies(): MutableLiveData<MoviesResponse>


    /**
     * On error
     */
    fun error()

}