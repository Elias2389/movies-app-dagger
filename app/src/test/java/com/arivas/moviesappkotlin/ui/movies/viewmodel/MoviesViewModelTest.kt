package com.arivas.moviesappkotlin.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.NullPointerException

@RunWith(JUnit4::class)
class MoviesViewModelTest {
    @Mock
    lateinit var moviesObservable: MoviesObservable

    @Mock
    lateinit var listResult: MutableLiveData<List<ResultsItem>>

    lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesViewModel = MoviesViewModel(moviesObservable)
    }

    @Test
    fun shouldBeNull() {
        //Mockito.`when`(moviesViewModel.getPopularMovies()).thenReturn(null)
        Assert.assertNotNull(moviesViewModel.getPopularMovies().value)
        Assert.assertTrue(moviesViewModel.getPopularMovies().hasObservers())
    }

    @Test
    fun shouldSuccessWithValidListOfPopularMoviesTest() {

    }
}