package com.arivas.moviesappkotlin.ui.movies.presenter

import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.interactor.MoviesInteractor
import com.arivas.moviesappkotlin.ui.movies.view.MoviesView

class MoviesPresenterImpl(private val moviesServices: MoviesServices,
                          private val interactor: MoviesInteractor): MoviesPresenter {

    lateinit var view: MoviesView

    init {
        setPresenter()
    }

    override fun popularMovies() {
        interactor.popularMovies()
    }

    override fun successPopularMovies(movies: MoviesResponse) {
        view.successPopularMovies(movies)
    }

    override fun error() {
        view.error()
    }

    override fun getView(moviesView: MoviesView) {
        view = moviesView
    }

    override fun setPresenter() {
        interactor.getPresenter(this)
    }
}