package com.dex.movieapp.data.source.local

import com.dex.movieapp.data.entities.Movie
import com.dex.movieapp.data.models.MovieModel
import com.dex.movieapp.data.source.MoviesDataSource
import com.dex.movieapp.utils.AppExecutors


class MoviesLocalDataSource constructor(val appExecutors: AppExecutors,
                                        val moviesDao: MoviesDao) : MoviesDataSource() {


    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        appExecutors.diskIO.execute {
            val movieDtoList = ArrayList<MovieModel>()

            val movies = moviesDao.getMovies()

            movies.forEach {
                movieDtoList.add(MovieModel(it.id, it.title, it.year, it.poster, it.overview))
            }

            appExecutors.mainThread.execute {

                if (movies.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onMoviesLoaded(movieDtoList)
                }
            }
        }
    }


    override fun saveMovie(movieModel: MovieModel) {

        appExecutors.diskIO.execute { moviesDao.insertMovie(Movie(movieModel.id, movieModel.title, movieModel.releaseYear, movieModel.posterPath, movieModel.overview)) }
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