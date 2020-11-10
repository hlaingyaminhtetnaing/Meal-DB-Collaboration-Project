package com.hlaingyaminhtetnaing.mealdbproject.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemPopular
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemUpcoming
import com.hlaingyaminhtetnaing.mealdbproject.ui.popular.PopularAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_playing.view.*

class UpComingAdapter : RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>(){

    var clickListener: ClickListener? = null

    fun setOnClickListener (clickListener:ClickListener){
        this.clickListener = clickListener
    }

    private var upComingList : List<ResultsItemUpcoming> = ArrayList()

    inner class UpComingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }
        lateinit var result: ResultsItemUpcoming
        fun bind(result: ResultsItemUpcoming){
            this.result = result
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + result.posterPath)
                .into(itemView.imgPlayingMovie)
            itemView.txtPlayingMovieName.text = result.title
        }

        override fun onClick(p0: View?) {
           clickListener?.onClick(result)
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

    interface ClickListener {
        fun onClick(result: ResultsItemUpcoming)
    }

}