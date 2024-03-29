package com.example.movieapp.data.datasource

import com.example.movieapp.data.model.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache():List<Movie>

    suspend fun saveMoviesToCashe(movie: List<Movie>)
}