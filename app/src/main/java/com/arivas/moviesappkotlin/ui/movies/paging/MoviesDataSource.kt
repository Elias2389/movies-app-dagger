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

class MoviesDataSource: PageKeyedDataSource<Long, ResultsItem>() {
    private val moviesServices: MoviesServices? = null
    private val firstPage: Long = 1

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Long>,
                             callback: LoadInitialCallback<Long, ResultsItem>) {
        getCall(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                callback.onResult(response.results, null, firstPage + 1L)
            },{ error ->
                Log.e("Error:", error.message)
            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        TODO("Not yet implemented")
    }

    private fun getCall(page: Int): Observable<MoviesResponse> {
        return moviesServices?.getPopularMoviesByPaging(page)!!
    }
}