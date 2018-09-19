package com.dex.movieapp.data.source.remote

import com.dex.movieapp.data.models.MovieList
import com.dex.movieapp.data.source.MoviesDataSource
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


object MoviesRemoteDataSource : MoviesDataSource() {


    /**
     *  will fetch movies from server here
     */
    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        MoviesNetworkApi.create().getMovies().enqueue(object : Callback<MovieList> {

            override fun onFailure(call: Call<MovieList>?, t: Throwable?) {

                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {

                callback.onMoviesLoaded(response?.body()!!.movieModelList)
            }

        })

    }


}


