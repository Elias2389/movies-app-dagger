package com.arivas.moviesappkotlin.ui.movies.viewmodel

import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @Mock
    lateinit var moviesViewModel: MoviesViewModel

    @Before
    public fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesViewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    public fun apiShouldBeNullTest() {
        Mockito.`when`(moviesRepository.getPopularMovies()).thenReturn(null)
        Assert.assertNull(moviesViewModel.getPopularMovies())
    }

}