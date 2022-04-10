package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.MovieType
import com.udemy.tmdbtest.data.model.models.replace
import com.udemy.tmdbtest.repository.MovieRepository
import javax.inject.Inject

class CheckForPopularMoviesPersistence @Inject constructor(private val repository: MovieRepository) {

    suspend fun call(list: List<MovieModel>, networkAvailable: Boolean) {
        if (networkAvailable) {
            val listIds = list.map { it.movieId }
            val existInDb = repository.getExistedMoviesFromDb(listIds)
            val notExistInDb = list.filter { o1 -> existInDb.none { o2 -> o2.movieId == o1.movieId } }.toList()
            var listToUpdate: List<MovieModel> = existInDb + notExistInDb
            listToUpdate.forEach {
                val newMovie = it
                if(!it.type.contains(MovieType.POPULAR.name)){
                    newMovie.type += ",${MovieType.POPULAR.name}"
                    listToUpdate = listToUpdate.replace(it, newMovie)
                }
            }
            repository.insertMovies(listToUpdate)

        }
    }
}