package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CheckForPopularMoviesPersistenceTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var checkForPopularMoviesPersistence: CheckForPopularMoviesPersistence

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        checkForPopularMoviesPersistence = CheckForPopularMoviesPersistence(repository)
    }

    @Test
    fun `when the network connection is not available should do nothing`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForPopularMoviesPersistence.call(movieList,false)
        //Then
        coVerify(exactly = 0) { repository.insertMovies(any()) }
        coVerify(exactly = 0) { repository.getExistedMoviesFromDb(any()) }

    }

    @Test
    fun `when the network connection is available check the movies that persist in database and save the popular movie list that not exist to database`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForPopularMoviesPersistence.call(movieList,true)
        //Then
        coVerify(exactly = 1) { repository.insertMovies(any()) }
        coVerify(exactly = 1) { repository.getExistedMoviesFromDb(any()) }

    }
}