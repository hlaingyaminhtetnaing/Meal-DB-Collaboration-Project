package com.hlaingyaminhtetnaing.mealdbproject.ui.toprated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemTopRated
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_playing.view.*

class TopRateAdapter : RecyclerView.Adapter<TopRateAdapter.TopRateViewHolder> () {

    var clickListener : ClickListener? = null

    fun setOnClickListener(clickListener : ClickListener){
        this.clickListener = clickListener
    }

    private var topRatedList : List<ResultsItemTopRated> = ArrayList()

    inner class TopRateViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

       lateinit var result:ResultsItemTopRated

        fun bind(result : ResultsItemTopRated){
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

    fun resultList(topRatedList : List<ResultsItemTopRated>){
        this.topRatedList = topRatedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playing,parent,false)
        return TopRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRateViewHolder, position: Int) {
        holder.bind(topRatedList[position])
    }

    override fun getItemCount(): Int {
        return topRatedList.size
    }

    interface ClickListener {
        fun onClick(result : ResultsItemTopRated)
    }
}