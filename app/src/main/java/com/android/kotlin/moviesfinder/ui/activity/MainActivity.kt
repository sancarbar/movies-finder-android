package com.android.kotlin.moviesfinder.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.kotlin.dependencyinjectionhilt.databinding.ActivityMainBinding
import com.android.kotlin.moviesfinder.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setClickListeners()
        addLiveDataObservers()

    }

    private fun addLiveDataObservers() {
        viewModel.movieQueryResultLiveData.observe(this) { moviesList ->
            moviesList?.forEach {
                Log.d("Developer", it.title)
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setClickListeners() {
        binding.searchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.queryMoviesData(binding.searchQuery.text.toString())
        }
    }


}

