package com.dex.movieapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dex.movieapp.R


import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import com.dex.movieapp.data.models.MovieModel
import com.dex.movieapp.databinding.ItemMovieBinding


/**
 * Adapter for the list of the movieModels
 * @property context Context in which the application is running
 */
class MoviesAdapter(private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    /**
     * The list of posts of the adapter
     */
    private var movieModels: List<MovieModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemMovieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(p0: MovieViewHolder, position: Int) {
        p0.bind(movieModels[position])
    }


    override fun getItemCount(): Int {
        return movieModels.size
    }


    /**
     * Updates the list of movieModels of the adapter
     * @param movieModels the new list of movieModels of the adapter
     */
    fun updateMovies(movieModels: List<MovieModel>) {
        this.movieModels = movieModels
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for movie item
     */
    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a movieModel into the view
         */
        fun bind(movieModel: MovieModel) {
            binding.movieModel = movieModel
            binding.executePendingBindings()
        }
    }
}
