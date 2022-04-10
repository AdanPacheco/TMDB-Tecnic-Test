package com.udemy.tmdbtest.repository

import com.udemy.tmdbtest.data.database.dao.MovieDao
import com.udemy.tmdbtest.data.database.dao.VideoDao
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.MovieType
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.data.model.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService, private val movieDao: MovieDao, private val videoDao: VideoDao
) {

    suspend fun getPopularMoviesFromApi(page: Int): List<MovieModel> {
        return api.getPopularMovies(page)
    }

    suspend fun getNowPlayingMoviesFromApi(page: Int): List<MovieModel> {
        return api.getNowPlayingMovies(page)
    }

    suspend fun getUpcomingMoviesFromApi(page: Int): List<MovieModel> {
        return  api.getUpcomingMovies(page)
    }

    suspend fun getMovieVideosFromApi(movieId: Long): List<VideoModel> {
        return api.getMovieVideos(movieId)
    }

    suspend fun getPopularMoviesFromDatabase(page: Int): List<MovieModel> {
        return movieDao.getPopularMovies(page,MovieType.POPULAR)
    }

    suspend fun getNowPlayingMoviesFromDatabase(page: Int): List<MovieModel> {
        return movieDao.getNowPlayingMovies(page,MovieType.PLAYING_NOW)
    }

    suspend fun getUpcomingMoviesFromDatabase(page: Int): List<MovieModel> {
        return movieDao.getUpcomingMovies(page,MovieType.UPCOMING)
    }

    suspend fun getMovieVideosFromDatabase(movieId: Long): List<VideoModel> {
        return videoDao.getMovieVideos(movieId)
    }

    suspend fun insertMovies(movies: List<MovieModel>) {
        movieDao.insertAllMovies(movies)
    }

    suspend fun insertVideos(videos: List<VideoModel>) {
        videoDao.insertAllVideos(videos)
    }

    suspend fun getExistedMoviesFromDb(list:List<Long>):List<MovieModel>{
        return movieDao.getMoviesWhereExist(list)
    }

}