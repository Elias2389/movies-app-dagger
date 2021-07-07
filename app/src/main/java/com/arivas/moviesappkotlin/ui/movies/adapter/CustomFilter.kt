package com.arivas.moviesappkotlin.ui.movies.adapter

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import android.widget.Filter
import com.arivas.moviesappkotlin.common.dto.ResultsItem

class CustomFilter(private val popularMoviesRecyclerView: PopularMoviesRecyclerView,
                   private val moviesList: List<ResultsItem>): Filter() {
    var originalList: List<ResultsItem> = moviesList
    var filteredList: ArrayList<ResultsItem> = arrayListOf()

    @SuppressLint("DefaultLocale")
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        filteredList.clear()
        val results = FilterResults()

        if (constraint?.isEmpty()!!) {
            filteredList.addAll(originalList)
        } else {
            val filterPattern = constraint.toString().toLowerCase().trim()
            originalList.forEach {
                if (it.title.toLowerCase().contains(filterPattern)) {
                    filteredList.add(it)
                }
            }
        }

        results.values = filteredList
        results.count = filteredList.size
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
       // popularMoviesRecyclerView.moviesList.clear()
        if (results != null) {
            try {
               // popularMoviesRecyclerView.moviesList.addAll(results.values as ArrayList<ResultsItem>)
                popularMoviesRecyclerView.notifyDataSetChanged()
            } catch (error: TypeCastException) {
                error.stackTrace
            }
        }
    }
}