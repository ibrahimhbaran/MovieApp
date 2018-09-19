package com.dex.movieapp.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dex.movieapp.data.entities.Movie

/**
 *  The room database that contains Movies
 */
@Database(entities = [(Movie::class)], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {

        private var INSTANCE: MoviesDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): MoviesDatabase {

            synchronized(lock) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MoviesDatabase::class.java, "Movies.db")
                            .build()
                }
            }

            return INSTANCE!!

        }
    }

}