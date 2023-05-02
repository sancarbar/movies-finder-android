package com.android.kotlin.moviesfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlin.moviesfinder.network.dto.MovieDto
import com.android.kotlin.moviesfinder.network.service.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(private val moviesService: MoviesService, private val apiKey: String) :
    ViewModel() {


    val movieQueryResultLiveData = MutableLiveData<List<MovieDto>?>()

    fun queryMoviesData(query: String) {
        viewModelScope.launch {

            val response = moviesService.searchMovies(query, apiKey)
            if (response.isSuccessful) {
                val movies = response.body()

                val searchResults = movies!!.searchResults

                if (searchResults == null || searchResults.isEmpty()) {
                    movieQueryResultLiveData.postValue(null)
                } else {
                    movieQueryResultLiveData.postValue(searchResults)
                }
            } else {
                println(response.errorBody())
            }
        }
    }

}