package com.udemy.tmdbtest.di

import android.content.Context
import androidx.room.Room
import com.udemy.tmdbtest.data.database.TmdbDatabase
import com.udemy.tmdbtest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)= Room.databaseBuilder(context,TmdbDatabase::class.java,Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db:TmdbDatabase)= db.getMovieDao()

    @Singleton
    @Provides
    fun provideVideoDao(db:TmdbDatabase)= db.getVideoDao()
}