package com.hlaingyaminhtetnaing.mealdbproject.ui.latestmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hlaingyaminhtetnaing.mealdbproject.R

class LatestMovieFragment : Fragment() {

    private lateinit var lastestMovieViewModel: LastestMovieViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lastestMovieViewModel =
                ViewModelProvider(this).get(LastestMovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        return root
    }
}