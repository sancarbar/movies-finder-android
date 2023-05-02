package com.android.kotlin.moviesfinder.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.kotlin.dependencyinjectionhilt.databinding.ActivityMainBinding
import com.android.kotlin.moviesfinder.network.dto.MovieDto
import com.android.kotlin.moviesfinder.ui.adapter.MoviesAdapter
import com.android.kotlin.moviesfinder.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding

    private val moviesAdapter = MoviesAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureRecyclerView()
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setClickListeners()
        addLiveDataObservers()
    }


    private fun addLiveDataObservers() {
        viewModel.movieQueryResultLiveData.observe(this) { moviesList ->
            updateMovieSearchResults(
                moviesList
            )
        }
    }


    private fun setClickListeners() {
        binding.searchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.queryMoviesData(binding.searchQuery.text.toString())
        }
    }


    private fun updateMovieSearchResults(moviesList: List<MovieDto>?) {
        runOnUiThread {
            binding.progressBar.visibility = View.GONE
            if (moviesList != null) {
                binding.emptyView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                moviesAdapter.update(moviesList)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            }
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = moviesAdapter
    }
}

