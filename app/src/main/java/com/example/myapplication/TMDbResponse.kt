package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class TMDbResponse(
    @SerializedName("results") val results: List<Movie>
)

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$posterPath"
}
