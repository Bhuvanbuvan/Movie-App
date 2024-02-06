package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usescases.GetMoviesUseCase
import com.example.movieapp.domain.usescases.UpdateMoviesUseCase

class ViewModelFactory(private val getMoviesUseCase: GetMoviesUseCase,private val updateMoviesUseCase: UpdateMoviesUseCase)
    :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(getMoviesUseCase,updateMoviesUseCase) as T
    }
}