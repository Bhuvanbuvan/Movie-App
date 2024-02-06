package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id:Int,

    @SerializedName("overview")
    val overview:String,

    @SerializedName("poster_path")
    val poster_path:String,

    @SerializedName("release_date")
    val release_date:String,

    @SerializedName("title")
    val title:String
)
