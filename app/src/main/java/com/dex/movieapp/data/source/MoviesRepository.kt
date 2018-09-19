package com.dex.movieapp.data.source

import android.content.Context
import android.util.Log
import com.dex.movieapp.data.models.MovieModel
import com.dex.movieapp.data.source.local.MoviesDatabase
import com.dex.movieapp.data.source.local.MoviesLocalDataSource
import com.dex.movieapp.data.source.remote.MoviesRemoteDataSource
import com.dex.movieapp.utils.AppExecutors
import com.dex.movieapp.utils.CACHE_TIME_PERIOD
import com.dex.movieapp.utils.Prefs

/**
 * class that will provide data from remote or local
 *
 */

class MoviesRepository(context: Context) : MoviesDataSource() {


    private var localDataSource: MoviesLocalDataSource? = null

    private var remoteDataSource: MoviesRemoteDataSource? = null


    private var prefs: Prefs? = null
    /**
     * This variable has public visibility so it can be accessed from tests.
     */
    var cachedMovies: LinkedHashMap<Int, MovieModel> = LinkedHashMap()

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    var cacheIsDirty = false


    init {
        prefs = Prefs(context)
        localDataSource = MoviesLocalDataSource.getInstance(AppExecutors(), MoviesDatabase.getInstance(context).movieDao())
        remoteDataSource = MoviesRemoteDataSource

    }

    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        // read from cache immediately
        if (cachedMovies.isNotEmpty() && !cacheIsDirty) {

            callback.onMoviesLoaded(ArrayList(cachedMovies.values))
            return
        }
        // if cache is dirty we need  fetch data from  network
        if (cacheIsDirty) {

            getMoviesFromRemote(callback)

        } else {

            localDataSource?.getMovies(object : MoviesDataSource.LoadMoviesCallback {
                override fun onMoviesLoaded(movies: List<MovieModel>) {

                    callback.onMoviesLoaded(movies)

                }

                override fun onDataNotAvailable() {

                    getMoviesFromRemote(callback)

                }


            })

        }

    }

    private fun getMoviesFromRemote(callback: MoviesDataSource.LoadMoviesCallback) {

        remoteDataSource?.getMovies(object : MoviesDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<MovieModel>) {
                refreshCache(movies)
                refreshLocalDataSource(movies)
                callback.onMoviesLoaded(ArrayList(cachedMovies.values))
            }

            override fun onDataNotAvailable() {

                callback.onDataNotAvailable()

            }

        })
    }

    private fun refreshLocalDataSource(movies: List<MovieModel>) {

        localDataSource?.deleteAllMovies()

        for (movie in movies) {
            localDataSource?.saveMovie(movie)
        }


    }


    override fun deleteAllMovies() {

        localDataSource?.deleteAllMovies()
        cachedMovies.clear()
    }

    private fun refreshCache(movies: List<MovieModel>) {
        // set cache period for every 10 minutes to refresh it
        prefs?.cacheTime = System.currentTimeMillis() + CACHE_TIME_PERIOD

        cachedMovies.clear()
        movies.forEach {
            cachedMovies[it.id] = it
        }
        cacheIsDirty = false

        Log.v(TAG, "app cache refreshed ")
    }


    companion object {

        val TAG = MoviesRepository::class.java.simpleName

        private var INSTANCE: MoviesRepository? = null


        @JvmStatic
        fun getInstance(context: Context): MoviesRepository {

            if (INSTANCE == null) {

                synchronized(MoviesRepository::class.java) {

                    INSTANCE = MoviesRepository(context)

                }


            }
            return INSTANCE!!
        }


    }

}
