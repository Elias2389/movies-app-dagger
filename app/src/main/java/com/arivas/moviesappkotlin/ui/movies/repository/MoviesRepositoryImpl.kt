package com.arivas.moviesappkotlin.ui.movies.repository


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MoviesRepositoryImpl(private val moviesServices: MoviesServices): MoviesRepository {

    private var popularMovies = MutableLiveData<MoviesResponse>()

    @SuppressLint("CheckResult")
    override fun popularMovies() {
        getCall()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
               popularMovies.value = response
            },{ error ->
                Log.e("Error,", error.message)
            })
    }

    private fun getCall(): Observable<MoviesResponse> {
        return moviesServices.getPopularMovies(BuildConfig.API_KEY)
    }

    override fun error() {
    }

    override fun getPopularMovies(): MutableLiveData<MoviesResponse> {
        return popularMovies
    }
}