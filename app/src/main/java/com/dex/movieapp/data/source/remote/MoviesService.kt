package com.dex.movieapp.data.source.remote


import com.dex.movieapp.data.models.MovieList
import retrofit2.Call
import retrofit2.http.GET

/**
 * movies service created with retrofit ,will make request to get a list of movies with endpoints
 */
interface MoviesService{

    @GET("/api/movies")
    fun getMovies(): Call<MovieList>
}
