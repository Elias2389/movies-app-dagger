package com.arivas.moviesappkotlin.ui.movies.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.ui.moviesdetail.view.MoviesDetailActivity
import com.facebook.drawee.view.SimpleDraweeView

class PopularMoviesRecyclerView(private val results: List<ResultsItem>,
                                private val context: Context):
    RecyclerView.Adapter<PopularMoviesRecyclerView.ViewHolder>(), Filterable {
    var moviesList: ArrayList<ResultsItem> = ArrayList()

    init {
        moviesList.addAll(results)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.card_item,
            p0, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.title.text = moviesList.get(i).title
        viewHolder.img.setImageURI(BuildConfig.BASE_URL_IMAGES + moviesList.get(i).posterPath)
        goToDetail(viewHolder, moviesList.get(i))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: SimpleDraweeView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }

    private fun goToDetail(viewHolder: ViewHolder, resultsItem: ResultsItem) {
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, MoviesDetailActivity::class.java)
            val option: ActivityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                    context as Activity,
                    Pair<View, String>(viewHolder.img,"shared_photo"))

            intent.putExtra("resultsItem", resultsItem)
            ActivityCompat.startActivity(context, intent, option.toBundle())
            //context.startActivity(intent, option.toBundle())
        }
    }

    override fun getFilter(): Filter {
        moviesList.clear()
        moviesList.addAll(results)
        return CustomFilter(this, moviesList)
    }

}