package com.hlaingyaminhtetnaing.mealdbproject.ui.toprated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.adapter.TopRateAdapter
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import kotlinx.android.synthetic.main.fragment_top_rated.*


class TopRatedFragment : Fragment() {

    lateinit var viewModel: TopRatedViewModel
    lateinit var topRatedAdapter : TopRateAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)

        viewModel.loadTopRated()
        topRatedAdapter = TopRateAdapter()

        recyclerTopRated.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = topRatedAdapter
        }

        viewModel.getTopRated().observe(
            viewLifecycleOwner, Observer {
                topRatedAdapter.updateList(it.results as List<ResultsItemTopRated>)
            }
        )
    }

}