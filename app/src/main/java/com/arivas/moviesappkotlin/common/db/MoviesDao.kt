package com.arivas.moviesappkotlin.common.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arivas.moviesappkotlin.common.dto.ResultsItem

@Dao
interface MoviesDao {

    /**
     * Method to get data from DB
     *
     * @return results
     */
    @Query("SELECT * FROM movies")
    fun getMoviesFromDatabase(): LiveData<List<ResultsItem>>

    /**
     * Insert results in DB
     *
     * @param resultsItem
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesToDatabase(resultsItem: List<ResultsItem>)
}