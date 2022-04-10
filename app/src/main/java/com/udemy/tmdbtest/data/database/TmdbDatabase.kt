package com.udemy.tmdbtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udemy.tmdbtest.data.database.dao.MovieDao
import com.udemy.tmdbtest.data.database.dao.VideoDao
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.VideoModel

@Database(entities = [MovieModel::class, VideoModel::class], version = 1,exportSchema = false)
abstract class TmdbDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getVideoDao(): VideoDao
}