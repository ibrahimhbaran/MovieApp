package com.dex.movieapp.data.source.remote

import com.dex.movieapp.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Network API for getting request from remote data source
 */
class MoviesNetworkApi {

    companion object MoviesApi {
        fun create(): MoviesService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(MoviesService::class.java);
        }
    }

}