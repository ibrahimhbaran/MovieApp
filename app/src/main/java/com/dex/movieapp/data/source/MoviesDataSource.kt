package com.dex.movieapp.data.source

import com.dex.movieapp.data.models.MovieModel


open class  MoviesDataSource {

    interface LoadMoviesCallback {

        fun onMoviesLoaded(movieModels: List<MovieModel>)

        fun onDataNotAvailable()

    }


   open fun getMovies(callback: LoadMoviesCallback){}

   open fun saveMovie(movieModel: MovieModel){}

   open fun deleteAllMovies(){}

}



