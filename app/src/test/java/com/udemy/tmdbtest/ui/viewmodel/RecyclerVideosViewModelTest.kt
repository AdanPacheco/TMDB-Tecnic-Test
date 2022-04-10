package com.udemy.tmdbtest.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.domain.GetMovieVideosUserCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
class RecyclerVideosViewModelTest {

    @RelaxedMockK
    private lateinit var getMovieVideosUserCase: GetMovieVideosUserCase

    private lateinit var recyclerVideosViewModel: RecyclerVideosViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recyclerVideosViewModel = RecyclerVideosViewModel(getMovieVideosUserCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when getMovieVideosUserCase call return a list of videos set on livedata`() = runTest {
        val videoList:List<VideoModel> = listOf()
        coEvery { getMovieVideosUserCase.call(1,true) } returns videoList

        recyclerVideosViewModel.fetchMovieVideos(1,true)

        assert(recyclerVideosViewModel.videoList.value == videoList)
    }
}