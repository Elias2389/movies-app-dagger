package com.arivas.moviesappkotlin.ui.moviesdetail.view

import android.app.ActivityOptions
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.bumptech.glide.Glide


class MoviesDetailActivity : AppCompatActivity() {

    private lateinit var imageDetail: ImageView
    private lateinit var description: TextView
    private lateinit var titleMovie: TextView
    private lateinit var data: ResultsItem

    private val RESULTS_ITEMS: String = "resultsItem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        setup()
        setViews()
        setData()
    }

    private fun setViews() {
        titleMovie = findViewById(R.id.title_movie)
        description = findViewById(R.id.description)
        imageDetail = findViewById(R.id.image_detail)
    }

    private fun setData() {
        ViewCompat.setTransitionName(imageDetail, "shared_photo")
        Glide.with(this)
            .load(BuildConfig.BASE_URL_IMAGES + data.posterPath)
            .into(imageDetail)
        titleMovie.text = data.title
        description.text = data.overview
    }

    private fun setup() {
        data = intent.getSerializableExtra(RESULTS_ITEMS) as ResultsItem
    }
}
