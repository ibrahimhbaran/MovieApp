package com.dex.movieapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import com.dex.movieapp.data.entities.Movie
import com.dex.movieapp.data.source.MoviesDataSource
import com.dex.movieapp.data.source.MoviesRepository


class MoviesViewModel(val context: Application) : AndroidViewModel(context) {


    val moviesLiveData = MutableLiveData<List<Movie>>()


    fun loadMovies(searchTerm: String): LiveData<List<Movie>> {

        MoviesRepository.getInstance(context).getMovies(object : MoviesDataSource.LoadMoviesCallback {

            override fun onMoviesLoaded(movies: List<Movie>) {

                if (searchTerm.isNotEmpty()) {

                    moviesLiveData.value = movies.filter { it.title.contains(searchTerm, true) }

                } else {

                    moviesLiveData.value = movies

                }


            }

            override fun onDataNotAvailable() {

            }

        })

        return moviesLiveData


    }


}