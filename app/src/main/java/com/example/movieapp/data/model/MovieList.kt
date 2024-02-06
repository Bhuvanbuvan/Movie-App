package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("results")
    val Movies:List<Movie>
)
