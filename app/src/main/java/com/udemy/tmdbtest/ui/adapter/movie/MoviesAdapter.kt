package com.udemy.tmdbtest.ui.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udemy.tmdbtest.R
import com.udemy.tmdbtest.data.model.models.MovieModel

class MoviesAdapter(
    private val movieList: MutableList<MovieModel>,
    private val onMovieClicked:(MovieModel)->Unit
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.render(movieList[position],onMovieClicked)
    }

    override fun getItemCount(): Int = movieList.size

    fun appendMovies(movies: List<MovieModel>) {
        movieList.addAll(movies)
        notifyItemRangeInserted(movieList.size, movies.size - 1)
    }
}