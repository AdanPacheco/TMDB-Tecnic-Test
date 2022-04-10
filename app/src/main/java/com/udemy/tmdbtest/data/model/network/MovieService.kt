package com.udemy.tmdbtest.data.model.network

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.MovieType
import com.udemy.tmdbtest.data.model.models.VideoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val apiClient: MovieApiClient) {

    suspend fun getPopularMovies(page: Int = 1): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getPopularMovies(page = page)
            response.body()?.setPagingData(MovieType.POPULAR)
            response.body()?.movies ?: emptyList()
        }
    }

    suspend fun getNowPlayingMovies(page: Int = 1): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getNowPlayingMovies(page = page)
            response.body()?.setPagingData(MovieType.PLAYING_NOW)
            response.body()?.movies ?: emptyList()
        }
    }

    suspend fun getUpcomingMovies(page: Int = 1): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getUpcomingMovies(page = page)
            response.body()?.setPagingData(MovieType.UPCOMING)
            response.body()?.movies ?: emptyList()
        }
    }

    suspend fun getMovieVideos(movieId: Long): List<VideoModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getMovieVideos(movieId)
            response.body()?.setMovieIdData()
            response.body()?.videos ?: emptyList()
        }
    }
}