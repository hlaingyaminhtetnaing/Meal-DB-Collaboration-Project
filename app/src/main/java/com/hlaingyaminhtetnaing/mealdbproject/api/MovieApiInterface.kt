package com.hlaingyaminhtetnaing.mealdbproject.api

import com.hlaingyaminhtetnaing.mealdbproject.model.*
import retrofit2.Call
import retrofit2.http.*

interface MovieApiInterface {
    @GET("latest")
    fun getLastest(
        @Query("api_key") api_key:String
    ):Call<ModelLatest>

    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key:String
    ):Call<ModelNowPlaying>
    @GET("popular")
    fun getPopular(
        @Query("api_key") api_key:String
    ):Call<ModelPopular>
    @GET("top_rated")
    fun getTopRated(
        @Query("api_key") api_key:String
    ):Call<ModelTopRated>
    @GET("upcoming")
    fun getUpcoming(
        @Query("api_key") api_key:String
    ):Call<ModelUpcoming>

    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id")movie_id:Int,
        @Query("api_key") api_key:String
    ):Call<ModelDetail>
}