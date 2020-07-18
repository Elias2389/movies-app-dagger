package com.arivas.moviesappkotlin.ui.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.databinding.ActivityMainBinding
import com.arivas.moviesappkotlin.databinding.CardItemPlaceholderBinding
import com.arivas.moviesappkotlin.databinding.ContentScrollingBinding
import com.arivas.moviesappkotlin.ui.movies.adapter.PopularMoviesRecyclerView
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModel
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import javax.inject.Inject


class MoviesActivity : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: PopularMoviesRecyclerView
    private lateinit var mainBinding: ActivityMainBinding

    @Inject lateinit var moviesViewModel: MoviesViewModel
    @Inject lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesList: List<ResultsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setInjectComponent()
        setup()
        getMovies()
    }

    private fun setup() {
        setupSearchView()
        handlerCollapsing()
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
        showLoading()
    }

    private fun getViewModelProvider(): MoviesViewModel {
        return ViewModelProviders
            .of(this, MoviesViewModelFactory(moviesRepository))
            .get(MoviesViewModel::class.java)
    }

    private fun successPopularMovies(movies: List<ResultsItem>) {
       moviesList = movies
       hideLoading()
       setupAdapter()
    }

    private fun setupAdapter() {
        layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 3)
        mainBinding.contentView.recyclerView.layoutManager = layoutManager
        adapter = PopularMoviesRecyclerView(moviesList, this)
        mainBinding.contentView.recyclerView.adapter = adapter
    }

    private fun showLoading() {
        mainBinding.contentView.recyclerView.visibility = View.GONE
        mainBinding.contentView.cardItem.animationView.apply {
            visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun hideLoading() {
        Handler().postDelayed({
            mainBinding.contentView.recyclerView.visibility = View.VISIBLE
            mainBinding.contentView.cardItem.animationView.apply {
                visibility = View.GONE
                cancelAnimation()
            }
        }, 3000)
    }

    private fun handlerCollapsing() {
        var isShow = false
        var scrollRange: Int = -1

        mainBinding.appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                mainBinding.collapsingToolbarMovies.title = resources.getString(R.string.title_movies)
                isShow = true
            } else if (isShow){
                mainBinding.collapsingToolbarMovies.title = " "
                isShow = false
            }
        })
    }

    private fun setupSearchView() {
        mainBinding.searchMovies.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}
