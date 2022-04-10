package com.udemy.tmdbtest.data.database.dao

import androidx.room.*
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.MovieType

@Dao
interface MovieDao {


    @Query("SELECT * FROM movie_table WHERE type LIKE '%' || :type || '%' AND page=:page AND posterPath IS NOT NULL ")
    suspend fun getNowPlayingMovies(page:Int,type:MovieType): List<MovieModel>

    @Query("SELECT * FROM movie_table WHERE type LIKE '%' || :type || '%' AND page=:page AND posterPath IS NOT NULL")
    suspend fun getPopularMovies(page:Int,type:MovieType): List<MovieModel>

    @Query("SELECT * FROM movie_table WHERE type LIKE '%' || :type || '%' AND page=:page AND posterPath IS NOT NULL")
    suspend fun getUpcomingMovies(page:Int,type:MovieType): List<MovieModel>

    @Query("SELECT * FROM movie_table WHERE movieId IN (:listIds)")
    suspend fun getMoviesWhereExist(listIds:List<Long>):List<MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieModel>): List<Long>


}
