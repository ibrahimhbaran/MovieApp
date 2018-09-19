package com.dex.movieapp.data.source.remote


import com.dex.movieapp.data.models.MovieList
import com.dex.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET

/**
 * movies service created with retrofit ,will make request to get a list of movies with endpoints
 */
interface MoviesService{

    @GET("movie/popular?api_key=$API_KEY")
    fun getMovies(): Call<MovieList>
}
