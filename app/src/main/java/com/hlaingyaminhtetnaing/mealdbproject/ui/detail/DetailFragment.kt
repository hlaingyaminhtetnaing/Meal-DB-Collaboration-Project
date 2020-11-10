package com.hlaingyaminhtetnaing.mealdbproject.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    lateinit var viewModel : DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        var messagearg = arguments?.let {
            DetailFragmentArgs.fromBundle(it)
        }

        var id : Int = messagearg?.id!!.toInt()

        viewModel.loadDetail(id)

        viewModel.getDetail().observe(
            viewLifecycleOwner, Observer {
                detailMovieTitle.text = it.title
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" +it.backdropPath)
                    .fit()
                    .into(detailBackImage)

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" +it.posterPath)
                    .fit()
                    .into(detailImage)
                detailVoteCount.text = it.voteCount.toString()
                detailVoteAverage.text = it.voteAverage.toString()
                detailOverview.text = it.overview.toString()
            }
        )

    }



}