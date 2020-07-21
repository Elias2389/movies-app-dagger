package com.arivas.moviesappkotlin.ui.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arivas.moviesappkotlin.common.dto.MoviesResponse

class MoviesDataSourceFactory: DataSource.Factory<Long, MoviesResponse>() {
    var movies: MutableLiveData<MoviesDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, MoviesResponse> {
        val moviesDataSource = MoviesDataSource()
        movies.postValue(moviesDataSource)
        return null!!
    }
}