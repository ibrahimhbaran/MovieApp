package com.dex.movieapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import com.dex.movieapp.data.models.MovieModel
import com.dex.movieapp.data.source.MoviesDataSource
import com.dex.movieapp.data.source.MoviesRepository


class MoviesViewModel(val context: Application) : AndroidViewModel(context) {


    val moviesLiveData = MutableLiveData<List<MovieModel>>()


    fun loadMovies(searchTerm: String): LiveData<List<MovieModel>> {

        MoviesRepository.getInstance(context).getMovies(object : MoviesDataSource.LoadMoviesCallback {

            override fun onMoviesLoaded(movies: List<MovieModel>) {

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