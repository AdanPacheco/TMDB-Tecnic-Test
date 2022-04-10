package com.udemy.tmdbtest.domain


import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.repository.MovieRepository
import javax.inject.Inject

class GetMovieVideosUserCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun call(movieId: Long,networkAvailable: Boolean): List<VideoModel> {
        return if(networkAvailable){
            val videos = repository.getMovieVideosFromApi(movieId)
            repository.insertVideos(videos)
            videos
        }else{
            repository.getMovieVideosFromDatabase(movieId)
        }
    }
}