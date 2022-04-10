package com.udemy.tmdbtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.domain.GetMovieVideosUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclerVideosViewModel @Inject constructor(private val getMovieVideosUserCase: GetMovieVideosUserCase):ViewModel(){

    var videoList = MutableLiveData<List<VideoModel>>()

    fun fetchMovieVideos(movieId:Long,networkAvailable: Boolean){
        viewModelScope.launch {
            val result = getMovieVideosUserCase.call(movieId,networkAvailable)
            videoList.postValue(result)
        }
    }
}