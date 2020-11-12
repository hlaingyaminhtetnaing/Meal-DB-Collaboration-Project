package com.hlaingyaminhtetnaing.mealdbproject.ui.popular

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
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemPopular
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchAdapter
import com.hlaingyaminhtetnaing.mealdbproject.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_playing.view.*
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() , PopularAdapter.ClickListener ,SearchAdapter.ClickListener{

    private lateinit var searchViewModel : SearchViewModel
    private lateinit var searchAdapter : SearchAdapter

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var popularAdapter: PopularAdapter
    lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_popular, container, false)


        return root
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        popularAdapter = PopularAdapter()
        searchAdapter = SearchAdapter()


        popularRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = popularAdapter
        }
        popularViewModel.getPopular().observe(viewLifecycleOwner, Observer { popular ->
            popularAdapter.resultPlay(popular.results as List<ResultsItemPopular>)
        })
        popularAdapter.setOnClickListener(this)
        searchAdapter.setOnClickListener(this)


    }

    override fun onResume() {
        super.onResume()
        popularViewModel.getLoadingPlay()

    }

    override fun onClick(play: ResultsItemPopular) {
        var action = PopularFragmentDirections.actionNavHomeToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.searrch)
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
                        recyclerSearch.apply {
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
                        Log.i("TextChange: >>>>>>", newText!!)
                    }
                    return true
                }

            })
        }
    }

    override fun onClickSearch(play: ResultsItemSearch) {
        var action = PopularFragmentDirections.actionNavHomeToDetailFragment(play.id.toString())
        findNavController().navigate(action)
    }


}