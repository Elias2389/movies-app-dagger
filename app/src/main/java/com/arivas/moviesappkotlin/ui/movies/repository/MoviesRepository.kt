package com.arivas.moviesappkotlin.ui.movies.repository

import androidx.lifecycle.LiveData
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource

interface MoviesRepository {
    /**
     * Get popular movies
     */
    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>>

    /**
     * Handler Error
     */
    fun handlerError(error: Throwable)

    /**
     * Insert in database
     */
    fun setMoviesInDatabase(resultsItem: List<ResultsItem>)
}