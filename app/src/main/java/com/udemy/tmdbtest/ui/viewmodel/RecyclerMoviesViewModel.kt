package com.udemy.tmdbtest.ui.viewmodel


import androidx.lifecycle.*
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclerMoviesViewModel @Inject constructor(
    private val getPopularMoviesUserCase: GetPopularMoviesUserCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUserCase,
    private val getUpcomingMoviesUserCase: GetUpcomingMoviesUserCase,
    private val checkForPopularMoviesPersistence: CheckForPopularMoviesPersistence,
    private val checkForPlayingNowMoviesPersistence: CheckForPlayingNowMoviesPersistence,
    private val checkForUpcomingMoviesPersistence: CheckForUpcomingMoviesPersistence
) : ViewModel() {

    var popularMovieList = MutableLiveData<List<MovieModel>>()
    var nowPlayingMovieList = MutableLiveData<List<MovieModel>>()
    var upcomingMovieList = MutableLiveData<List<MovieModel>>()

    private val _rvStatePopular = MutableLiveData<Int>()
    var rvStatePopular: LiveData<Int> = _rvStatePopular

    private val _rvStatePlayingNow = MutableLiveData<Int>()
    var rvStatePlayingNow: LiveData<Int> = _rvStatePlayingNow

    private val _rvStateUpcoming = MutableLiveData<Int>()
    var rvStateUpcoming: LiveData<Int> = _rvStateUpcoming


    fun fetchPopularMoviesData(page: Int, networkAvailable: Boolean) {
      viewModelScope.launch {
            val result = getPopularMoviesUserCase.call(page, networkAvailable)
            checkForPopularMoviesPersistence.call(result,networkAvailable)
            popularMovieList.postValue(result)
            _rvStatePopular.value = 0
        }
    }

    fun fetchNowPlayingMoviesData(page: Int,networkAvailable: Boolean) {
        viewModelScope.launch {
            val result = getNowPlayingMoviesUseCase.call(page,networkAvailable)
            checkForPlayingNowMoviesPersistence.call(result,networkAvailable)
            nowPlayingMovieList.postValue(result)
            _rvStatePlayingNow.value = 0
        }
    }

    fun fetchUpcomingMoviesData(page: Int,networkAvailable: Boolean) {
        viewModelScope.launch {
            val result = getUpcomingMoviesUserCase.call(page,networkAvailable)
            checkForUpcomingMoviesPersistence.call(result,networkAvailable)
            upcomingMovieList.postValue(result)
            _rvStateUpcoming.value = 0
        }
    }

}