package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CheckForUpcomingMoviesPersistenceTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var checkForUpcomingMoviesPersistence: CheckForUpcomingMoviesPersistence

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        checkForUpcomingMoviesPersistence = CheckForUpcomingMoviesPersistence(repository)
    }

    @Test
    fun `when the network connection is not available should do nothing`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForUpcomingMoviesPersistence.call(movieList,false)
        //Then
        coVerify(exactly = 0) { repository.insertMovies(any()) }
        coVerify(exactly = 0) { repository.getExistedMoviesFromDb(any()) }

    }

    @Test
    fun `when the network connection is available check the movies that persist in database and save the upcoming movie list that not exist to database`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForUpcomingMoviesPersistence.call(movieList,true)
        //Then
        coVerify(exactly = 1) { repository.insertMovies(any()) }
        coVerify(exactly = 1) { repository.getExistedMoviesFromDb(any()) }

    }
}