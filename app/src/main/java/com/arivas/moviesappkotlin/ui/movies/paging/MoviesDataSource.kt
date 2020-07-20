package com.arivas.moviesappkotlin.ui.movies.paging

import androidx.paging.PageKeyedDataSource
import com.arivas.moviesappkotlin.common.dto.ResultsItem

class MoviesDataSource: PageKeyedDataSource<Long, ResultsItem>() {

    override fun loadInitial(params: LoadInitialParams<Long>,
                             callback: LoadInitialCallback<Long, ResultsItem>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ResultsItem>) {
        TODO("Not yet implemented")
    }
}