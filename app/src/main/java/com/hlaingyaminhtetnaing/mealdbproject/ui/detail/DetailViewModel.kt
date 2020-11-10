package com.hlaingyaminhtetnaing.mealdbproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){
    private var detail : MutableLiveData<ModelDetail> = MutableLiveData()

    fun getDetail() : LiveData<ModelDetail> = detail

    fun loadDetail(id : Int){
        var apiClient = MovieClient()
        var apiCall = apiClient.getDetail(
            id,"9ef2ef916822104b0887b6c1419c6e7c"
        )

        apiCall.enqueue(
            object : Callback<ModelDetail> {
                override fun onResponse(call: Call<ModelDetail>, response: Response<ModelDetail>) {
                    detail.value = response.body()
                }

                override fun onFailure(call: Call<ModelDetail>, t: Throwable) {

                }

            }
        )
    }
}