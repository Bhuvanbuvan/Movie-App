package com.example.movieapp.domain.usescases

import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute():List<Movie>?=movieRepository.getMovies()
}