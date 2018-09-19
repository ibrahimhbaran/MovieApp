package com.dex.movieapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieList(@field:SerializedName("results") val movieModelList: List<MovieModel>)