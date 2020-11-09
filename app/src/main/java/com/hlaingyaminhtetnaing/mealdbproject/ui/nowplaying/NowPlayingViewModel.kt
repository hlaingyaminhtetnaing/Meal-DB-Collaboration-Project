package com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelNowPlaying
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {
    private var playingModel:MutableLiveData<ModelNowPlaying> = MutableLiveData()

    fun getNowPlaying(): LiveData<ModelNowPlaying> =playingModel

    fun getLoadingPlay() {
        var apiClient = MovieClient()
        var call = apiClient.getNowPlaying(
            "9ef2ef916822104b0887b6c1419c6e7c"
        )
        call.enqueue(object :Callback<ModelNowPlaying>{
            override fun onResponse(
                call: Call<ModelNowPlaying>,
                response: Response<ModelNowPlaying>
            ) {
                playingModel.value = response.body()
            }

            override fun onFailure(call: Call<ModelNowPlaying>, t: Throwable) {
                Log.d("Error>>>", t.toString())
            }

        })

    }
}