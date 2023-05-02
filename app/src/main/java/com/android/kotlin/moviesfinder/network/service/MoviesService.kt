package com.android.kotlin.moviesfinder.network.service

import com.android.kotlin.moviesfinder.network.dto.SearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apikey: String
    ): Response<SearchResultDto>


}