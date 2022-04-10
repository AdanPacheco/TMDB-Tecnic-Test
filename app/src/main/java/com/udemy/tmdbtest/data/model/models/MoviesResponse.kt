package com.udemy.tmdbtest.data.model.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieModel>,
    @SerializedName("total_pages") val pages: Int
){
    fun setPagingData(type:MovieType){
        movies.forEach {
            it.page = page
            it.type = type.name
        }
    }
}

