package com.example.movieapp.data.datasourceimpl

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apikey:String):MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> =   tmdbService.getPopularMovies(apikey)
}