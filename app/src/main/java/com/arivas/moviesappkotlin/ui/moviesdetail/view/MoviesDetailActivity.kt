package com.arivas.moviesappkotlin.ui.moviesdetail.view

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import android.widget.TextView
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.facebook.drawee.view.SimpleDraweeView

class MoviesDetailActivity : AppCompatActivity() {

    private var description: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        description = findViewById(R.id.description)
        val imageDetail: SimpleDraweeView = findViewById(R.id.image_detail)

        val data: ResultsItem  = intent.getSerializableExtra("resultsItem") as ResultsItem


        imageDetail.setImageURI(BuildConfig.BASE_URL_IMAGES + data.posterPath)
     //   ViewCompat.setTransitionName(imageDetail, "shared_photo")
        description?.text = data.overview
    }
}
