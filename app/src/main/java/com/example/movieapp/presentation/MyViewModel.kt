package com.example.movieapp.presentation

import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.usescases.GetMoviesUseCase
import com.example.movieapp.domain.usescases.UpdateMoviesUseCase

class MyViewModel(private val getMoviesUseCase: GetMoviesUseCase,
                  private val updateMoviesUseCase: UpdateMoviesUseCase):ViewModel() {

        fun getMovies()= liveData {
            val movieList = getMoviesUseCase.execute()
            emit(movieList)
        }
    fun updateMovie()= liveData {
        val movieList=updateMoviesUseCase.execute()
        emit(movieList)
    }
}