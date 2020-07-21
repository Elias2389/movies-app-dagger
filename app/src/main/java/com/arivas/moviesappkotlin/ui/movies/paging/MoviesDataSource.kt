package com.arivas.moviesappkotlin.ui.movies.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesDataSource(val moviesServices: MoviesServices): PageKeyedDataSource<Long, ResultsItem>() {
    private val FIRST_PAGE: Long = 1
    private val PAGE_SIZE: Int = 4

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Long>,
                             callback: LoadInitialCallback<Long, ResultsItem>) {
        getCall(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                callback.onResult(response.results,null, 2L)
            },{ error ->
                Log.e("Error loadInitial:", error.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        getCall(params.key.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                callback.onResult(response.results, params.key - 1)
            },{ error ->
                Log.e("Error loadBefore:", error.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        val a = params.key
        getCall(params.key.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                callback.onResult(response.results, params.key + 1)

            },{ error ->
                Log.e("Error loadAfter:", error.message)
            })
    }

    private fun getCall(page: Int): Observable<MoviesResponse> {
        return moviesServices.getPopularMoviesByPaging(page)
    }
}