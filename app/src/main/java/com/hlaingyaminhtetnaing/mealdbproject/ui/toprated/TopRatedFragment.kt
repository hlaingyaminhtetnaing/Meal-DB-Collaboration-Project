package com.hlaingyaminhtetnaing.mealdbproject.ui.toprated

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemNowPlaying
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying.NowPlayingAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying.NowPlayingViewModel
import com.hlaingyaminhtetnaing.mealdbproject.ui.popular.PopularFragmentDirections
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_playing.*
import kotlinx.android.synthetic.main.fragment_top_rated.*



class TopRatedFragment : Fragment(),TopRateAdapter.ClickListener,SearchAdapter.ClickListener {


   private lateinit var viewModel: TopRatedViewModel
   private lateinit var topRatedAdapter : TopRateAdapter
    private  lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    lateinit var searchView: SearchView
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
        searchViewModel=ViewModelProvider(this).get(SearchViewModel::class.java)
        searchAdapter= SearchAdapter()

        recyclerTopRated.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topRatedAdapter
        }

        viewModel.getTopRated().observe(
            viewLifecycleOwner, Observer {
                topRatedAdapter.resultList(it.results as List<ResultsItemTopRated>)
            }
        )
        topRatedAdapter.setOnClickListener(this)
        searchAdapter.setOnClickListener(this)
    }
    override fun onResume() {
        super.onResume()
        viewModel.loadTopRated()
    }


    override fun onClick(play: ResultsItemTopRated) {
        var action = TopRatedFragmentDirections.actionNavTopratedToDetailFragment(play.id.toString())
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
        var action = TopRatedFragmentDirections.actionNavTopratedToDetailFragment(play.id.toString())
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
                        topSearchRecycler.apply {
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