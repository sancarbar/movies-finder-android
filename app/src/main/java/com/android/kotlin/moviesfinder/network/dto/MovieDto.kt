package com.android.kotlin.moviesfinder.network.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)
