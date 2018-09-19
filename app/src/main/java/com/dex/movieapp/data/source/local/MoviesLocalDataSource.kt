package com.dex.movieapp.data.source.local

import com.dex.movieapp.data.entities.Movie
import com.dex.movieapp.data.source.MoviesDataSource
import com.dex.movieapp.utils.AppExecutors


class MoviesLocalDataSource constructor(val appExecutors: AppExecutors,
                                        val moviesDao: MoviesDao) : MoviesDataSource {


    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        appExecutors.diskIO.execute {
            val movies = moviesDao.getMovies()

            appExecutors.mainThread.execute {

                if (movies.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onMoviesLoaded(movies)
                }
            }
        }
    }


    override fun saveMovie(movie: Movie) {

        appExecutors.diskIO.execute { moviesDao.insertMovie(movie) }
    }

    override fun deleteAllMovies() {

        appExecutors.diskIO.execute { moviesDao.deleteAllMovies() }
    }


    companion object {

        private var INSTANCE: MoviesLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, moviesDao: MoviesDao): MoviesLocalDataSource {

            if (INSTANCE == null) {

                synchronized(MoviesLocalDataSource::class) {

                    INSTANCE = MoviesLocalDataSource(appExecutors, moviesDao)
                }
            }

            return INSTANCE!!

        }

    }

}