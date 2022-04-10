package com.udemy.tmdbtest.domain

import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.repository.MovieRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieVideosUserCaseTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private lateinit var getMovieVideosUserCase: GetMovieVideosUserCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMovieVideosUserCase = GetMovieVideosUserCase(repository)
    }

    @Test
    fun `when the network connection is not available then get movies videos from database`() = runBlocking {
       val movieVideosList:List<VideoModel> = listOf()
        //Given
        coEvery { repository.getMovieVideosFromDatabase(1) } returns movieVideosList

        //When
        val result = getMovieVideosUserCase.call(1, false)

        //Then
        coVerify(exactly = 0) { repository.getMovieVideosFromApi(1) }
        coVerify(exactly = 0) { repository.insertVideos(any()) }
        coVerify(exactly = 1) { repository.getMovieVideosFromDatabase(1) }
        assert(movieVideosList==result)
    }

    @Test
    fun `when the network connection is available then get movies videos from api and save it to database`() = runBlocking {
        val movieVideosList:List<VideoModel> = listOf()
        //Given
        coEvery { repository.getMovieVideosFromDatabase(1) } returns movieVideosList

        //When
        val result = getMovieVideosUserCase.call(1, true)

        //Then
        coVerify(exactly = 1) { repository.getMovieVideosFromApi(1) }
        coVerify(exactly = 1) { repository.insertVideos(any()) }
        coVerify(exactly = 0) { repository.getMovieVideosFromDatabase(1) }
        assert(movieVideosList==result)
    }
    }

