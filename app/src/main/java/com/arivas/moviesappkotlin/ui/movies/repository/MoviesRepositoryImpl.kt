package com.arivas.moviesappkotlin.ui.movies.repository

import androidx.lifecycle.LiveData
import com.arivas.moviesappkotlin.common.db.MoviesDao
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.NetworkBoundResource
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import io.reactivex.Observable

class MoviesRepositoryImpl(private val moviesServices: MoviesServices,
                           private val moviesDao: MoviesDao): MoviesRepository {

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