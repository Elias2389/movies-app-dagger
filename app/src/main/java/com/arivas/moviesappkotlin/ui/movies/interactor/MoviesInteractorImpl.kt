package com.arivas.moviesappkotlin.ui.movies.interactor


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.network.RetrofitService
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject


class MoviesInteractorImpl(private val presenter: MoviesPresenter,
                          private val moviesServices: MoviesServices): MoviesInteractor {

    @SuppressLint("CheckResult")
    override fun popularMovies() {
        getCall()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                successPopularMovies(response)
            },{ error ->
                Log.e("Error,", error.message)
                error()
            })
    }

    override fun successPopularMovies(movies: MoviesResponse) {
        presenter.successPopularMovies(movies)
    }

    override fun error() {
        presenter.error()
    }

    private fun getCall(): Observable<MoviesResponse> {
        return moviesServices.getPopularMovies(BuildConfig.API_KEY)
    }
}