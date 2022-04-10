package com.udemy.tmdbtest.data.model.network

import com.udemy.tmdbtest.data.model.models.MoviesResponse
import com.udemy.tmdbtest.data.model.models.VideosResponse
import com.udemy.tmdbtest.utils.Constants
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(
        @Path("movieId") movieId:Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<VideosResponse>
}