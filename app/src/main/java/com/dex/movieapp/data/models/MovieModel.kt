package com.dex.movieapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieModel(@field:SerializedName("id") var id: Int,
                      @field:SerializedName("title") var title: String,
                      @field:SerializedName("release_date") var releaseYear: String,
                      @field:SerializedName("poster_path") var posterPath: String,
                      @field:SerializedName("overview") var overview: String
)