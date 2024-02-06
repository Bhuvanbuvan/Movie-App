package com.example.movieapp.data

import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) :MovieRepository{
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }



    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies=getMoviesFromApi()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCashe(newListOfMovies)

        return newListOfMovies
    }





    suspend  fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList:List<Movie>
        try {
            val response= movieRemoteDataSource.getMovies()
            val body=response.body()
            if (body!= null){
                movieList= body.Movies
            }
        }catch (exeption: Exception){

        }
        return movieList

    }

    suspend fun getMoviesFromRoom():List<Movie>{
        lateinit var movieList:List<Movie>

        try {
            movieList=movieLocalDataSource.getMoviesFromDB()
        }catch (exeption:Exception){

        }
        if (movieList.size > 0){
            return movieList
        }else{
            movieList=getMoviesFromApi()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }


    private suspend fun getMoviesFromCache(): List<Movie>? {

        lateinit var movieList:List<Movie>

        try {
            movieList=movieCacheDataSource.getMoviesFromCache()

        }catch (exception:Exception){

        }
        if (movieList.size>0){
            return movieList
        }else{
            movieList=getMoviesFromRoom()
            movieCacheDataSource.saveMoviesToCashe(movieList)
        }
        return movieList
    }
}