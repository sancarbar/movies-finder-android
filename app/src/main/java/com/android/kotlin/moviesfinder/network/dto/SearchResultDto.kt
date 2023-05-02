package com.android.kotlin.moviesfinder.network.dto

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("Search") val searchResults: List<MovieDto>,
    val totalResults: String
)