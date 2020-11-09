package com.hlaingyaminhtetnaing.mealdbproject.ui.toprated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelTopRated
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelUpcoming
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TopRatedViewModel : ViewModel () {
    private var topRated : MutableLiveData<ModelTopRated> = MutableLiveData()

    fun getTopRated() : LiveData<ModelTopRated> = topRated

    fun loadTopRated () {
        val apiClient = MovieClient()
        val apiCall = apiClient.getTopRated("9ef2ef916822104b0887b6c1419c6e7c")

        apiCall.enqueue(
            object : Callback <ModelTopRated>{
                override fun onResponse(
                    call: Call<ModelTopRated>,
                    response: Response<ModelTopRated>
                ) {
                    topRated.value = response.body()
                    Log.d("Toprate>>", response.body().toString())
                }

                override fun onFailure(call: Call<ModelTopRated>, t: Throwable) {

                }

            }
        )
    }
}