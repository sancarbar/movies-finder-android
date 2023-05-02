package com.android.kotlin.moviesfinder.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.kotlin.dependencyinjectionhilt.databinding.ActivityMainBinding
import com.android.kotlin.moviesfinder.network.service.MoviesService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var moviesService: MoviesService

    @Inject
    lateinit var apiKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()

    }


    private fun setClickListeners() {
        binding.searchButton.setOnClickListener {
            GlobalScope.launch {
                val query = binding.searchQuery.text.toString()
                val response = moviesService.searchMovies(query, apiKey)
                if (response.isSuccessful) {
                    response.body()?.searchResults?.forEach {
                        Log.d("Developer", it.title)
                    }
                }
            }

        }
    }


}

