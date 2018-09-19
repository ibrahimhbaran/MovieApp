package com.dex.movieapp.data.models

import com.google.gson.annotations.SerializedName
import com.dex.movieapp.data.entities.Movie

data class MovieList(@field:SerializedName("results") val movieList: List<Movie>)