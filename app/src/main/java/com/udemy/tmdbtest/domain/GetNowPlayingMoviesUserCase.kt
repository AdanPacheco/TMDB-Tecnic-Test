package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUserCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun call(page: Int, networkAvailable: Boolean): List<MovieModel> {
        return if (networkAvailable) {
            repository.getNowPlayingMoviesFromApi(page)
        } else {
            repository.getNowPlayingMoviesFromDatabase(page)
        }
    }
}