package com.udemy.tmdbtest.data.model.models

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("id") val movieId: Long,
    @SerializedName("results") val videos: List<VideoModel>
){
    fun setMovieIdData(){
        videos.forEach {
            it.movieId = movieId
        }
    }
}