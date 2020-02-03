package com.arivas.moviesappkotlin.ui.movies.repository


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.arivas.moviesappkotlin.common.db.MoviesDao
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.NetworkBoundResource
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MoviesRepositoryImpl(private val moviesServices: MoviesServices,
                           private val moviesDao: MoviesDao): MoviesRepository {


    @SuppressLint("CheckResult")
    override fun popularMovies() {
        getCall()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                setMoviesInDatabase(response.results).let {

                }
            },{ error ->
                Log.e("Error,", error.message)
                handlerError(error)
            })
    }

    override fun handlerError(error: Throwable) {
    }

    private fun getCall(): Observable<MoviesResponse> {
        return moviesServices.getPopularMovies()
    }

    override fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        return object : NetworkBoundResource<List<ResultsItem>, MoviesResponse>() {
            override fun loadFromDb(): LiveData<List<ResultsItem>> {
                return moviesDao.getMoviesFromDatabase()
            }

            override fun createCall(): Observable<MoviesResponse> {
                return getCall()
            }

            override fun saveCallResult(item: MoviesResponse) {
                moviesDao.insertMoviesToDatabase(item.results)
            }


        }.getAsLiveData()
    }

    override fun setMoviesInDatabase(resultsItem: List<ResultsItem>) {
        moviesDao.insertMoviesToDatabase(resultsItem)
    }
}