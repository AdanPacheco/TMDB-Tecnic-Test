package com.udemy.tmdbtest.ui.adapter.movie

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.databinding.ItemMovieBinding
import com.udemy.tmdbtest.utils.Constants

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun render(movie: MovieModel, onMovieClicked: (MovieModel) -> Unit) {
        Glide.with(binding.root.context).load(Constants.MOVIE_IMAGE_BASE_URL_342 + movie.posterPath).diskCacheStrategy(DiskCacheStrategy.DATA).transform(CenterCrop())
            .into(binding.itemMoviePoster)

        itemView.setOnClickListener {
            onMovieClicked(movie)
        }
    }
}
