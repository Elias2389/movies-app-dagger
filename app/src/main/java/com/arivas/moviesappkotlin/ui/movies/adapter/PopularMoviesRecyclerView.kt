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
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.R
import com.arivas.moviesappkotlin.common.dto.ResultsItem
import com.arivas.moviesappkotlin.databinding.CardItemBinding
import com.arivas.moviesappkotlin.ui.moviesdetail.view.MoviesDetailActivity
import com.bumptech.glide.Glide


class PopularMoviesRecyclerView: PagedListAdapter<ResultsItem,
        PopularMoviesRecyclerView.ViewHolder>(resultItemDiffCallback) {

    var context: Context? = null

    fun setData(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.card_item,
            p0, false
        )

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(getItem(i)!!, this.context!!)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardItemBinding.bind(itemView)

        fun bind(resultsItem: ResultsItem, context: Context) {
            binding.apply {
                title.text = resultsItem.title
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGES + resultsItem.posterPath)
                    .centerInside()
                    .into(image)
                goToDetail(context, resultsItem)
            }

        }
        private fun goToDetail(context: Context, resultsItem: ResultsItem) {
            binding.itemViewMovie.setOnClickListener {
                val intent = Intent(context, MoviesDetailActivity::class.java)
                val option: ActivityOptionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(
                        context as Activity,
                        Pair<View, String>(binding.image,"shared_photo"))

                intent.putExtra("resultsItem", resultsItem)
                ActivityCompat.startActivity(context, intent, option.toBundle())
            }
        }

    }

//    override fun getFilter(): Filter {
//        moviesList.clear()
//        moviesList.addAll(moviesList)
//        return CustomFilter(this, moviesList)
//    }


    companion object {
        val resultItemDiffCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}