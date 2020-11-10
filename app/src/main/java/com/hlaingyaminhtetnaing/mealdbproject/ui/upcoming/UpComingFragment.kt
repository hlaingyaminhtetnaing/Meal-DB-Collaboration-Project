package com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemUpcoming
import com.hlaingyaminhtetnaing.mealdbproject.ui.toprated.TopRateAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.toprated.TopRatedViewModel
import kotlinx.android.synthetic.main.fragment_up_coming.*
import com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming.UpComingFragmentDirections.Companion as UpComingFragmentDirections1


class UpComingFragment : Fragment() ,UpComingAdapter.ClickListener{
    lateinit var viewModel: UpComingViewModel
    lateinit var upComingAdapter: UpComingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(UpComingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_up_coming, container, false)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpComingViewModel::class.java)

        upComingAdapter = UpComingAdapter()

        recyclerUpComing.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = upComingAdapter
        }
        viewModel.getUpComing().observe(
            viewLifecycleOwner, Observer {
                upComingAdapter.resultList(it.results as List<ResultsItemUpcoming>)
            }
        )
        upComingAdapter.setOnClickListener(this)
    }

    override fun onResume() {
        viewModel.loadUpComing()
        super.onResume()
    }

    override fun onClick(result: ResultsItemUpcoming) {
        var action = UpComingFragmentDirections.actionNavUpcomingToDetailFragment(result.id.toString())
        findNavController().navigate(action)
    }

}