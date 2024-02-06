package com.example.movieapp.presentation.di

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usescases.GetMoviesUseCase
import com.example.movieapp.domain.usescases.UpdateMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository):UpdateMoviesUseCase{
        return UpdateMoviesUseCase(movieRepository)
    }
}