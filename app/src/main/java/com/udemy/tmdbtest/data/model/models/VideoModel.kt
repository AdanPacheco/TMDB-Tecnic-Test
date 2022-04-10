package com.udemy.tmdbtest.data.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "video_table")
data class VideoModel(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("site") val site: String,
    @SerializedName("movieId") var movieId:Long
){

}