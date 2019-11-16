package com.arivas.moviesappkotlin.ui.movies.interactor

import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenter

interface MoviesInteractor {
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
     * GetPresenter
     */
    fun getPresenter(moviesPresenter: MoviesPresenter)
}