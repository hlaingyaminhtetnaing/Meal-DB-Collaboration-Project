package com.hlaingyaminhtetnaing.mealdbproject.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemPopular
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , PopularAdapter.ClickListener{

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var popularAdapter: PopularAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_popular, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        popularAdapter = PopularAdapter()
        popularRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = popularAdapter
        }
        popularViewModel.getPopular().observe(viewLifecycleOwner, Observer { popular ->
            popularAdapter.resultPlay(popular.results as List<ResultsItemPopular>)
        })
        popularAdapter.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        popularViewModel.getLoadingPlay()
    }

    override fun onClick(play: ResultsItemPopular) {
        var action = PopularFragmentDirections.actionNavHomeToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }

}