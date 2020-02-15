package com.arivas.moviesappkotlin.ui.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.common.network.networkboundresource.Resource
import com.arivas.moviesappkotlin.ui.movies.adapter.PopularMoviesRecyclerView
import com.arivas.moviesappkotlin.ui.movies.model.MoviesObservable
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModel
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModelFactory
import io.supercharge.shimmerlayout.ShimmerLayout
import javax.inject.Inject


class MoviesActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<PopularMoviesRecyclerView.ViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var shimmerLayout: ShimmerLayout? = null
    private var container: LinearLayout? = null

    @Inject lateinit var moviesViewModel: MoviesViewModel
    @Inject lateinit var moviesObservable: MoviesObservable
    private lateinit var moviesList: List<ResultsItem>

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
        moviesViewModel
            .getPopularMovies()
            .observe(this, Observer {
                it.data?.let { result -> successPopularMovies(result) }
        })
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

    private fun successPopularMovies(movies: List<ResultsItem>) {
       moviesList = movies
       hideShimmer()
       setupAdapter()
    }

    private fun setupAdapter() {
        layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 3)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = getAdapter()
    }

    private fun getAdapter(): PopularMoviesRecyclerView {
        return PopularMoviesRecyclerView(moviesList, this)
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
