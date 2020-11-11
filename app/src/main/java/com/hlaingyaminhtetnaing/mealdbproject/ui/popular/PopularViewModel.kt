package com.hlaingyaminhtetnaing.mealdbproject.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelPopular
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel : ViewModel() {

    private var popularModel:MutableLiveData<ModelPopular> = MutableLiveData()

    fun getPopular(): LiveData<ModelPopular> =popularModel

    fun getLoadingPlay() {
        var apiClient = MovieClient()
        var call = apiClient.getPopular(
            "9ef2ef916822104b0887b6c1419c6e7c"
        )
        call.enqueue(object :Callback<ModelPopular>{
            override fun onResponse(call: Call<ModelPopular>, response: Response<ModelPopular>) {
                popularModel.value=response.body()
            }

            override fun onFailure(call: Call<ModelPopular>, t: Throwable) {
                Log.d("Error>>>", t.toString())
            }

        })
    }
}