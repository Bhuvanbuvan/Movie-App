package com.example.movieapp.data.api

import com.example.movieapp.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBService {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query(
            "api_key"
        ) apikey:String
    ):Response<MovieList>
}