package com.arivas.moviesappkotlin.ui.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arivas.moviesappkotlin.common.dto.ResultsItem

class MoviesDataSourceFactory(private val moviesDataSource: MoviesDataSource):
    DataSource.Factory<Long, ResultsItem>() {
    private val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Long, ResultsItem> {
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}