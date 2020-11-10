package com.hlaingyaminhtetnaing.mealdbproject.ui.toprated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemNowPlaying
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying.NowPlayingAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying.NowPlayingViewModel
import kotlinx.android.synthetic.main.fragment_playing.*
import kotlinx.android.synthetic.main.fragment_top_rated.*


class TopRatedFragment : Fragment() {

    lateinit var viewModel: TopRatedViewModel
    lateinit var topRatedAdapter : TopRateAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_top_rated, container, false)


        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)

        topRatedAdapter = TopRateAdapter()

        recyclerTopRated.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = topRatedAdapter
        }

        viewModel.getTopRated().observe(
            viewLifecycleOwner, Observer {
                topRatedAdapter.resultList(it.results as List<ResultsItemTopRated>)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTopRated()
    }
}