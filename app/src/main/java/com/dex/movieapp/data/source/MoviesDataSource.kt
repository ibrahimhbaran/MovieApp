package com.dex.movieapp.data.source

import com.dex.movieapp.data.entities.Movie


interface MoviesDataSource {

    interface LoadMoviesCallback {

        fun onMoviesLoaded(movies: List<Movie>)

        fun onDataNotAvailable()

    }


    fun getMovies(callback: LoadMoviesCallback)

    fun saveMovie(movie: Movie)

    fun deleteAllMovies()

}



