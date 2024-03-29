package com.example.movieapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun providesMovieDataBase(context: Context):TMDBDatabase{
        return Room
            .databaseBuilder(
                context,
                TMDBDatabase::class.java,
                "tmdb")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase):MovieDao{
        return tmdbDatabase.movieDao()
    }
}