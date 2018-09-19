package com.dex.movieapp.data.entities

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * romm movie table representation
 *
 * @param id      id of movie
 * @param title   title of movie
 * @param year    movie year
 * @param poster  movie poster
 */
@Entity(tableName = "movies", primaryKeys = ["id"])
data class Movie(var id: Int,
                 var title: String,
                 var year: String,
                 var poster: String,
                 var overview: String
)
