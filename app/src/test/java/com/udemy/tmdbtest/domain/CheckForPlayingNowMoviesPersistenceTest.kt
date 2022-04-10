package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CheckForPlayingNowMoviesPersistenceTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var checkForPlayingNowMoviesPersistence: CheckForPlayingNowMoviesPersistence

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        checkForPlayingNowMoviesPersistence = CheckForPlayingNowMoviesPersistence(repository)
    }

    @Test
    fun `when the network connection is not available should do nothing`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForPlayingNowMoviesPersistence.call(movieList,false)
        //Then
        coVerify(exactly = 0) { repository.insertMovies(any()) }
        coVerify(exactly = 0) { repository.getExistedMoviesFromDb(any()) }

    }

    @Test
    fun `when the network connection is available check the movies that persist in database and save the now playing movie list that not exist to database`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        checkForPlayingNowMoviesPersistence.call(movieList,true)
        //Then
        coVerify(exactly = 1) { repository.insertMovies(any()) }
        coVerify(exactly = 1) { repository.getExistedMoviesFromDb(any()) }

    }
}