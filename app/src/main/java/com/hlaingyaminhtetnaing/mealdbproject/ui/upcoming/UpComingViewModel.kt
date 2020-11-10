package com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelUpcoming
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpComingViewModel : ViewModel(){
    private var upComing : MutableLiveData<ModelUpcoming> = MutableLiveData()

    fun getUpComing() : LiveData<ModelUpcoming> = upComing

    fun loadUpComing () {
        val apiClient = MovieClient()
        val apiCall = apiClient.getUpcoming(
            "9ef2ef916822104b0887b6c1419c6e7c"
        )

        apiCall.enqueue(
            object  : Callback <ModelUpcoming>{
                override fun onResponse(
                    call: Call<ModelUpcoming>,
                    response: Response<ModelUpcoming>
                ) {
                    upComing.value = response.body()
                }

                override fun onFailure(call: Call<ModelUpcoming>, t: Throwable) {

                }

            }
        )
    }
}