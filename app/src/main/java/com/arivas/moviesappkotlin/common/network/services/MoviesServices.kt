package com.arivas.moviesappkotlin.common.network.services

import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {

    /**
     * Method to get popular movies from API REST
     *
     * @return response with popular movies
     */
    @GET("popular")
    fun getPopularMovies(): Observable<MoviesResponse>

    /**
     * Method to get popular movies from API REST
     *
     * @return response with popular movies by page
     */
    @GET("popular")
    fun getPopularMoviesByPaging(@Query("page") page: Int): Observable<MoviesResponse>
}