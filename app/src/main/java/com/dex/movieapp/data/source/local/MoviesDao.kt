package com.dex.movieapp.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dex.movieapp.data.entities.Movie

@Dao
interface MoviesDao {

    /**
     * Select all movies  from the movies  table.
     *
     * @return all movies.
     */
    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movie>


    /**
     * Select a movie by id.
     *
     * @param movieId the movie id.
     * @return the movie  with movieId.
     */
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: String): Movie?


    /**
     * Insert a movie into database. If the movie already exists, replace it.
     *
     * @param movie the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)


    /**
     * Delete all movies.
     */
    @Query("DELETE FROM movies") fun deleteAllMovies()

}
