package com.hlaingyaminhtetnaing.mealdbproject.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import kotlinx.android.synthetic.main.item_movie_recyclerview.view.*

class TopRateAdapter {
    class TopRateViewHolder (itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(result : ResultsItemTopRated){
            itemView.recyclertxt.text = result.
        }
    }
}