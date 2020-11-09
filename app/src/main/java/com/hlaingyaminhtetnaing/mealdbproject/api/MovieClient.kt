package com.hlaingyaminhtetnaing.mealdbproject.api

import com.hlaingyaminhtetnaing.mealdbproject.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieClient {
    private val BASE_URL="https://api.themoviedb.org/3/movie/"

    private var movieApiInterface:MovieApiInterface
    init {

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApiInterface=retrofit.create(MovieApiInterface::class.java)
    }

    fun getLastest(api_key: String): Call<ModelLatest> {
        return movieApiInterface.getLastest(api_key)
    }
    fun getNowPlaying(api_key:String): Call<ModelNowPlaying> {
        return movieApiInterface.getNowPlaying(api_key)
    }
    fun getPopular(api_key:String): Call<ModelPopular> {
        return movieApiInterface.getPopular(api_key)
    }
    fun getTopRated(api_key:String): Call<ModelTopRated> {
        return movieApiInterface.getTopRated(api_key)
    }
    fun getUpcoming(api_key: String): Call<ModelUpcoming> {
        return movieApiInterface.getUpcoming(api_key)
    }
    fun getDetail(movie_id:Int,api_key: String): Call<ModelDetail> {
        return movieApiInterface.getDetail(movie_id,api_key)
    }

}