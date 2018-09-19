package com.dex.movieapp.data.entities

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for movie , we can use either for room database  and gson deserialization
 *
 * @param id      id of movie
 * @param title   title of movie
 * @param year    movie year
 * @param poster  movie poster
 */
@Entity(tableName = "movies",primaryKeys = ["id"])
data class Movie(@field:SerializedName("id")  var id: Int,
                 @field:SerializedName("title") var title: String,
                 @field:SerializedName("release_date") var year: String,
                 @field:SerializedName("poster_path") var poster: String,
                 @field:SerializedName("overview") var overview: String
)
