package com.udemy.tmdbtest.data.model.models



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "movie_table")
data class MovieModel(
    @PrimaryKey
    @SerializedName("id") val movieId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("page") var page: Int,
    @SerializedName("type") var type: String
):Serializable


fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }