package com.android.kotlin.moviesfinder.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.kotlin.dependencyinjectionhilt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()

    }


    private fun setClickListeners() {
        binding.searchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            Log.d("Developer", "Search button clicked")

        }
    }


}

