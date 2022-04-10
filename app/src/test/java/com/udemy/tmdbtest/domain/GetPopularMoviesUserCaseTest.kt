package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUserCaseTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var getPopularMoviesUserCase: GetPopularMoviesUserCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularMoviesUserCase = GetPopularMoviesUserCase(repository)
    }

    @Test
    fun `when the network connection is not available then get popular movies from database`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        //Given
        coEvery { repository.getPopularMoviesFromDatabase(1) } returns movieList

        //When
        val result = getPopularMoviesUserCase.call(1, false)

        //Then
        coVerify(exactly = 0) { repository.getPopularMoviesFromApi(1) }
        coVerify(exactly = 1) { repository.getPopularMoviesFromDatabase(1) }
        assert(movieList==result)
    }

    @Test
    fun `when the network connection is available then get upcoming movies from api`() = runBlocking {

        val movieList: List<MovieModel>  = listOf()
        //Given
        coEvery { repository.getPopularMoviesFromApi(1) } returns movieList

        //When
        val result = getPopularMoviesUserCase.call(1, true)

        //Then
        coVerify(exactly = 1) { repository.getPopularMoviesFromApi(1) }
        coVerify(exactly = 0) { repository.getPopularMoviesFromDatabase(1) }
        assert(movieList==result)
    }
}
