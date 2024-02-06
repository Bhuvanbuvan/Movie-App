package com.example.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.domain.usescases.UpdateMoviesUseCase
import com.example.movieapp.presentation.di.Injector
import okhttp3.internal.notify
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var movieViewModel:MyViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        (application as Injector).createMoviesSubComponent().inject(this)

        movieViewModel=ViewModelProvider(this,factory).get(MyViewModel::class.java)
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= MovieAdapter()
        binding.recyclerView.adapter=adapter
        displayPopularMovies()

    }

    private fun displayPopularMovies() {
        binding.progressBar.visibility=View.VISIBLE
        val responseLiveData= movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility=View.GONE
            }else{
                binding.progressBar.visibility=View.GONE
                Toast.makeText(application,"No data are available",Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
            inflater.inflate(R.menu.update,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update->{
                UpdateMovies()
                true
            }else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
    private fun UpdateMovies() {
        binding.progressBar.visibility=View.VISIBLE
        val response=movieViewModel.updateMovie()
        response.observe(this, Observer {
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility=View.GONE
            }else{
                binding.progressBar.visibility=View.GONE
            }
        })
    }
}
