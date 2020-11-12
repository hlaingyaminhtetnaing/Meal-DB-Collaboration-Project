package com.hlaingyaminhtetnaing.mealdbproject.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_search.*

class SearchFragment : Fragment(), SearchAdapter.ClickListener {

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchAdapter : SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchAdapter = SearchAdapter()
        SearchRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = searchAdapter
        }


    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val menuItem = menu?.findItem(R.id.searrch)
        val searchManager =activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menuItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener (object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getLoadingPlay(newText.toString())
                SearchRecycler.apply {
                    layoutManager = GridLayoutManager(context,2)
                    adapter = searchAdapter
                }

                viewModel.getSearch().observe(
                    viewLifecycleOwner, Observer {
                        searchAdapter.resultPlay(it.results as List<ResultsItemSearch>)
                    })

                Log.d("querySearch>>", newText!!)
                return true
            }

        })
    }

    override fun onClickSearch(play: ResultsItemSearch) {

    }

}