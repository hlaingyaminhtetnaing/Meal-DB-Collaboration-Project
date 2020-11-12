package com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying

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
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemNowPlaying
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_playing.*

class NowPlayingFragment : Fragment(),NowPlayingAdapter.ClickListener,SearchAdapter.ClickListener{

    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private  lateinit var searchViewModel:SearchViewModel
    private lateinit var searchAdapter:SearchAdapter
    lateinit var searchView: SearchView
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
        searchViewModel=ViewModelProvider(this).get(SearchViewModel::class.java)
        searchAdapter= SearchAdapter()
        playingRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = nowPlayingAdapter
        }
        nowPlayingViewModel.getNowPlaying().observe(viewLifecycleOwner, Observer { playing ->
            nowPlayingAdapter.resultPlay(playing.results as List<ResultsItemNowPlaying>)
        })
        nowPlayingAdapter.setOnClickListener(this)
        searchAdapter.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewModel.getLoadingPlay()
    }


    override fun onClick(play: ResultsItemNowPlaying) {
        var action = NowPlayingFragmentDirections.actionNavGalleryToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (!searchView.isIconified) {
                    searchView.onActionViewCollapsed();
                }
            }

        })
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
                        nowSearchRecycler.apply {
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
    override fun onClickSearch(play: ResultsItemSearch) {
        var action=NowPlayingFragmentDirections.actionNavGalleryToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }


}