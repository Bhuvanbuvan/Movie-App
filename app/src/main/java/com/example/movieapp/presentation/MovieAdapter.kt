package com.example.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.ListItemBinding

class MovieAdapter():RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieList=ArrayList<Movie>()

    fun setList(movies:List<Movie>){
        movieList.clear()
        movieList.addAll(movies)
    }

    inner class MovieViewHolder(var binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.titleTextView.text=movie.title
            binding.descTextView.text=movie.overview

            val poster_url="https://image.tmdb.org/t/p/w500/"+ movie.poster_path
            Glide.with(binding.imageView.context)
                .load(poster_url)
                .into(binding.imageView)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}