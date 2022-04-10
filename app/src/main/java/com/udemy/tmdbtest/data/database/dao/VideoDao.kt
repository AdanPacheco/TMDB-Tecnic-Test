package com.udemy.tmdbtest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udemy.tmdbtest.data.model.models.VideoModel

@Dao
interface VideoDao {

    @Query("SELECT * FROM video_table WHERE movieId = :movieId")
    suspend fun getMovieVideos(movieId:Long): List<VideoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVideos(videos: List<VideoModel>): List<Long>
}