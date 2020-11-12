package com.hlaingyaminhtetnaing.mealdbproject.api

import com.hlaingyaminhtetnaing.mealdbproject.model.*
import retrofit2.Call
import retrofit2.http.*

interface MovieApiInterface {
    @GET("movie/latest")
    fun getLastest(
        @Query("api_key") api_key:String
    ):Call<ModelLatest>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key:String
    ):Call<ModelNowPlaying>
    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") api_key:String
    ):Call<ModelPopular>
    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") api_key:String
    ):Call<ModelTopRated>
    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") api_key:String
    ):Call<ModelUpcoming>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id")movie_id:Int,
        @Query("api_key") api_key:String
    ):Call<ModelDetail>
    @GET("search/movie")
    fun getSearch(
        @Query("api_key") api_key:String,
        @Query("query") query:String
    ): Call<ModelsSearch>
}