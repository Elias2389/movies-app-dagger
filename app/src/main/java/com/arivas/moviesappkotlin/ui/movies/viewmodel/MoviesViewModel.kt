package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.ui.movies.paging.MoviesDataSource
import com.arivas.moviesappkotlin.ui.movies.paging.MoviesDataSourceFactory
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    lateinit var resultList:LiveData<PagedList<ResultsItem>>
    private var moviesDataSourceFactory: MoviesDataSourceFactory = MoviesDataSourceFactory()

    fun getPopularMovies(): LiveData<PagedList<ResultsItem>> {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        resultList = LivePagedListBuilder<Long, ResultsItem>(moviesDataSourceFactory, config).build()

        return resultList
    }
}