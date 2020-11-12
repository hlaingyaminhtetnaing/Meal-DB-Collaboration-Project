package com.hlaingyaminhtetnaing.mealdbproject.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlaingyaminhtetnaing.mealdbproject.api.MovieClient
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelPopular
import com.hlaingyaminhtetnaing.mealdbproject.model.ModelsSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private var searchViewModel: MutableLiveData<ModelsSearch> = MutableLiveData()

    fun getSearch(): LiveData<ModelsSearch> =searchViewModel

    fun getLoadingPlay(query:String) {
        Log.d("query>>",query)
        var apiClient = MovieClient()
        var call = apiClient.getSearch(
            "9ef2ef916822104b0887b6c1419c6e7c",query
        )
        call.enqueue(object :Callback<ModelsSearch>{
            override fun onResponse(call: Call<ModelsSearch>, response: Response<ModelsSearch>) {
                searchViewModel.value=response.body()
            }

            override fun onFailure(call: Call<ModelsSearch>, t: Throwable) {
                Log.d("Error>>>", t.toString())
            }

        })
    }
}