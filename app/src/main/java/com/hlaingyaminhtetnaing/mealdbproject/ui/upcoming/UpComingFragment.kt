package com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemUpcoming
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchViewModel
import com.hlaingyaminhtetnaing.mealdbproject.ui.toprated.TopRateAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.toprated.TopRatedViewModel
import kotlinx.android.synthetic.main.fragment_top_rated.*
import kotlinx.android.synthetic.main.fragment_up_coming.*
import com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming.UpComingFragmentDirections.Companion as UpComingFragmentDirections1


class UpComingFragment : Fragment() ,UpComingAdapter.ClickListener,SearchAdapter.ClickListener{
    lateinit var viewModel: UpComingViewModel
    lateinit var upComingAdapter: UpComingAdapter
    private  lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    lateinit var searchView: SearchView
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
        searchViewModel=ViewModelProvider(this).get(SearchViewModel::class.java)
        searchAdapter= SearchAdapter()
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
        searchAdapter.setOnClickListener(this)
    }

    override fun onResume() {
        viewModel.loadUpComing()
        super.onResume()
    }

    override fun onClick(result: ResultsItemUpcoming) {
        var action = UpComingFragmentDirections.actionNavUpcomingToDetailFragment(result.id.toString())
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (!searchView.isIconified()) {
                    searchView.onActionViewCollapsed();
                }
            }

        })
    }
    override fun onClickSearch(play: ResultsItemSearch) {
        var action = UpComingFragmentDirections.actionNavUpcomingToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main,menu)
        val searchItem=menu.findItem(R.id.searrch)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        if (searchView != null) {

            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName));
            searchView.setOnCloseListener(object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    searchAdapter.clearData()
                    return true
                }

            })
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("TextChange: >>>>>>", query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty()!!) {
                        upSearchRecycler.apply {
                            layoutManager = GridLayoutManager(context, 2)
                            adapter = searchAdapter
                        }
                        searchViewModel.getLoadingPlay(newText.toString())
                        searchViewModel.getSearch().observe(
                            viewLifecycleOwner, Observer {
                                searchAdapter.resultPlay(it.results as List<ResultsItemSearch>)
                                Log.d("SearchResult>>", it.results.toString())
                            }
                        )
                        Log.i("TextChange: >>>>>>", newText)
                    }
                    return true
                }

            })
        }
    }

}