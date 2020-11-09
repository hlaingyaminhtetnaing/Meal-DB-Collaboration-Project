package com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemNowPlaying
import kotlinx.android.synthetic.main.fragment_playing.*

class NowPlayingFragment : Fragment(), NowPlayingAdapter.OnClickListener {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel =
            ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_playing, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        nowPlayingAdapter = NowPlayingAdapter()
        playingRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = nowPlayingAdapter
        }
        nowPlayingViewModel.getNowPlaying()?.observe(viewLifecycleOwner, Observer { playing ->
            nowPlayingAdapter.resultPlay(playing.results as List<ResultsItemNowPlaying>)
        })
    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewModel.getLoadingPlay()
    }

    override fun onClick(item: ResultsItemNowPlaying) {
        TODO("Not yet implemented")
    }

}