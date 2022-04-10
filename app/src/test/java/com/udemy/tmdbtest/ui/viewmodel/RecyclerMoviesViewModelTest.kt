package com.udemy.tmdbtest.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.domain.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecyclerMoviesViewModelTest {

    @RelaxedMockK
    private lateinit var getPopularMoviesUserCase: GetPopularMoviesUserCase

    @RelaxedMockK
    private lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUserCase

    @RelaxedMockK
    private lateinit var getUpcomingMoviesUserCase: GetUpcomingMoviesUserCase

    @RelaxedMockK
    private lateinit var checkForPopularMoviesPersistence: CheckForPopularMoviesPersistence

    @RelaxedMockK
    private lateinit var checkForPlayingNowMoviesPersistence: CheckForPlayingNowMoviesPersistence

    @RelaxedMockK
    private lateinit var checkForUpcomingMoviesPersistence: CheckForUpcomingMoviesPersistence

    private lateinit var recyclerMoviesViewModel: RecyclerMoviesViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recyclerMoviesViewModel = RecyclerMoviesViewModel(
            getPopularMoviesUserCase,
            getNowPlayingMoviesUseCase,
            getUpcomingMoviesUserCase,
            checkForPopularMoviesPersistence,
            checkForPlayingNowMoviesPersistence,
            checkForUpcomingMoviesPersistence
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getNowPlayingMoviesUseCase call return a list of movies set on livedata and checkForPopularMoviesPersistence`() = runTest {
        val movieList: List<MovieModel> = listOf()
        coEvery { getNowPlayingMoviesUseCase.call(1, true) } returns movieList

        recyclerMoviesViewModel.fetchNowPlayingMoviesData(1, true)

        coVerify(exactly = 1) { checkForPlayingNowMoviesPersistence.call(movieList,true) }
        assert(recyclerMoviesViewModel.nowPlayingMovieList.value == movieList)
    }

    @Test
    fun `when getPopularMoviesUserCase call return a list of movies set on livedata and checkForPopularMoviesPersistence`() = runTest {
        val movieList: List<MovieModel> = listOf()
        coEvery { getPopularMoviesUserCase.call(1, true) } returns movieList
        recyclerMoviesViewModel.fetchPopularMoviesData(1, true)
        coVerify(exactly = 1) { checkForPopularMoviesPersistence.call(movieList,true) }
        assert(recyclerMoviesViewModel.popularMovieList.value == movieList)
    }

    @Test
    fun `when checkForUpcomingMoviesPersistence call return a list of movies set on livedata and check`() = runTest {
        val movieList: List<MovieModel> = listOf()
        coEvery { getUpcomingMoviesUserCase.call(1, true) } returns movieList
        recyclerMoviesViewModel.fetchUpcomingMoviesData(1, true)
        coVerify(exactly = 1) { checkForUpcomingMoviesPersistence.call(movieList,true) }
        assert(recyclerMoviesViewModel.upcomingMovieList.value == movieList)
    }
}