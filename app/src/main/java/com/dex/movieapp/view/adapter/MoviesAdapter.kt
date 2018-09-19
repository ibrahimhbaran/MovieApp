package com.dex.movieapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dex.movieapp.R


import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import com.dex.movieapp.data.entities.Movie
import com.dex.movieapp.databinding.ItemMovieBinding
import com.dex.movieapp.utils.IMAGE_BASE_URL


/**
 * Adapter for the list of the movies
 * @property context Context in which the application is running
 */
class MoviesAdapter(private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    /**
     * The list of posts of the adapter
     */
    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemMovieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(p0: MovieViewHolder, position: Int) {
        p0.bind(movies[position])
    }


    override fun getItemCount(): Int {
        return movies.size
    }


    /**
     * Updates the list of movies of the adapter
     * @param movies the new list of movies of the adapter
     */
    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for movie item
     */
    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a movie into the view
         */
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.baseimageurl = IMAGE_BASE_URL
            binding.executePendingBindings()
        }
    }
}
