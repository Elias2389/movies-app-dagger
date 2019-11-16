package com.arivas.moviesappkotlin.ui.movies.presenter

import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.ui.movies.view.MoviesView

interface MoviesPresenter {
    /**
     * Get popular movies
     */
    fun popularMovies()

    /**
     * Success popular movies
     */
    fun successPopularMovies(movies: MoviesResponse)

    /**
     * On error
     */
    fun error()

    /**
     * Set view
     */
    fun getView(moviesView: MoviesView)

    /**
     * Set presenter
     */
    fun setPresenter()
}