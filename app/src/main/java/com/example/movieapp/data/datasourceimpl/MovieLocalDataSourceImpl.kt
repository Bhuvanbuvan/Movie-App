package com.example.movieapp.data.datasourceimpl

import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val dao: MovieDao):MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> = dao.getMovies()


    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch{
            dao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllMovies()
        }
    }
}
