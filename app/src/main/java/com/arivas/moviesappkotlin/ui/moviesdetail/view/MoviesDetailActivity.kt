package com.arivas.moviesappkotlin.ui.moviesdetail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.facebook.drawee.view.SimpleDraweeView

class MoviesDetailActivity : AppCompatActivity() {

    private var description: TextView? = null
    private var imageDetail: SimpleDraweeView? = null
    lateinit var data: ResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        setup()
        setViews()
    }

    private fun setViews() {
        description = findViewById(R.id.description)
        imageDetail = findViewById(R.id.image_detail)

        imageDetail?.setImageURI(BuildConfig.BASE_URL_IMAGES + data.posterPath)
        description?.text = data.overview
    }

    private fun setup() {
        data = intent.getSerializableExtra("resultsItem") as ResultsItem
    }
}
