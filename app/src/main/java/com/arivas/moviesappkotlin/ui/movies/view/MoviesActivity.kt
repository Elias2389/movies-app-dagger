package com.arivas.moviesappkotlin.ui.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.ui.movies.adapter.PopularMoviesRecyclerView
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModel
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModelFactory
import io.supercharge.shimmerlayout.ShimmerLayout
import javax.inject.Inject


class MoviesActivity : AppCompatActivity() {

    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var mAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>? = null
    private var layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager? = null
    private var shimmerLayout: ShimmerLayout? = null
    private var container: LinearLayout? = null

    @Inject lateinit var moviesViewModel: MoviesViewModel
    @Inject lateinit var moviesObservable: MoviesObservable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInjectComponent()
        setup()
        getMovies()
    }

    private fun setup() {
        setViews()
        moviesViewModel = getViewModelProvider()
    }

    private fun setInjectComponent() {
        (application as BaseApp).getComponent().inject(this)
    }

    private fun getMovies() {
        moviesViewModel.popularMovies().let {
            moviesViewModel
                .getPopularMovies()
                .observe(this, Observer {
                successPopularMovies(it)
            })
        }
        showShimmer()
    }

    private fun setViews() {
        shimmerLayout = findViewById(R.id.shimmer)
        container = findViewById(R.id.container_info)
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun getViewModelProvider(): MoviesViewModel {
        return ViewModelProviders
            .of(this, MoviesViewModelFactory(moviesObservable))
            .get(MoviesViewModel::class.java)
    }

    private fun successPopularMovies(movies: MoviesResponse) {
        hideShimmer()
        setupAdapter()
        setAdapter(movies.results)
    }

    private fun setupAdapter() {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
    }

    private fun setAdapter(results: List<ResultsItem>) {
        mAdapter = PopularMoviesRecyclerView(results, this)
        recyclerView?.adapter = mAdapter
    }

    private fun showShimmer() {
        shimmerLayout?.startShimmerAnimation()
        shimmerLayout?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
    }

    private fun hideShimmer() {
        recyclerView?.visibility = View.VISIBLE
        shimmerLayout?.visibility = View.GONE
        shimmerLayout?.stopShimmerAnimation()
    }

}
