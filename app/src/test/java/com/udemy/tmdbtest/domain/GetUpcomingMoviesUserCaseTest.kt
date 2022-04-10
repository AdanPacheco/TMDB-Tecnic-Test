package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetUpcomingMoviesUserCaseTest {

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var getUpcomingMoviesUserCase: GetUpcomingMoviesUserCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUpcomingMoviesUserCase = GetUpcomingMoviesUserCase(repository)
    }

    @Test
    fun `when the network connection is not available then get upcoming movies from database`() = runBlocking {

        val movieList: List<MovieModel> = listOf()
        //Given
        coEvery { repository.getUpcomingMoviesFromDatabase(1) } returns movieList

        //When
        val result = getUpcomingMoviesUserCase.call(1, false)

        //Then
        coVerify(exactly = 0) { repository.getUpcomingMoviesFromApi(1) }
        coVerify(exactly = 1) { repository.getUpcomingMoviesFromDatabase(1) }
        assert(movieList==result)
    }

    @Test
    fun `when the network connection is available then get upcoming movies from api`() = runBlocking {

        val movieList: List<MovieModel>  = listOf()
        //Given
        coEvery { repository.getUpcomingMoviesFromApi(1) } returns movieList

        //When
        val result = getUpcomingMoviesUserCase.call(1, true)

        //Then
        coVerify(exactly = 1) { repository.getUpcomingMoviesFromApi(1) }
        coVerify(exactly = 0) { repository.getUpcomingMoviesFromDatabase(1) }
        assert(movieList==result)
    }
}