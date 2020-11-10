package com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemUpcoming
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_playing.view.*

class UpComingAdapter : RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>(){

    private var upComingList : List<ResultsItemUpcoming> = ArrayList()

    class UpComingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(result: ResultsItemUpcoming){
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + result.posterPath)
                .into(itemView.imgPlayingMovie)
            itemView.txtPlayingMovieName.text = result.title
        }
    }

    fun resultList(upComingList : List<ResultsItemUpcoming>){
        this.upComingList = upComingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playing,parent,false)
        return UpComingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(upComingList[position])
    }

    override fun getItemCount(): Int {
        return upComingList.size
    }

}