package com.arivas.moviesappkotlin.ui.movies.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.dto.MoviesResponse
import com.arivas.moviesappkotlin.common.network.RetrofitService
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.adapter.PopularMoviesRecyclerView
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenter
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenterImpl
import io.supercharge.shimmerlayout.ShimmerLayout
import retrofit2.Retrofit
import javax.inject.Inject


class MoviesActivity : AppCompatActivity(), MoviesView {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var shimmerLayout: ShimmerLayout? = null
    private var container: LinearLayout? = null

    @Inject lateinit var moviesServices: MoviesServices
    @Inject lateinit var presenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BaseApp).getComponent().inject(this)

        setViews()
        setup()
    }

    private fun setup() {
        setView()
        popularMovies()
    }

    private fun setViews() {
        shimmerLayout = findViewById(R.id.shimmer)
        container = findViewById(R.id.container_info)
        recyclerView = findViewById(R.id.recycler_view)
    }

    override fun popularMovies() {
        presenter.popularMovies()
        showShimmer()
    }

    override fun successPopularMovies(movies: MoviesResponse) {
        hideShimmer()

        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager

        mAdapter = PopularMoviesRecyclerView(movies.results!!)
        recyclerView?.adapter = mAdapter
    }

    override fun error() {

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

    override fun setView() {
        presenter.getView(this)
    }

}
