package com.hlaingyaminhtetnaing.mealdbproject.ui.nowplaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemNowPlaying
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemPopular
import com.hlaingyaminhtetnaing.mealdbproject.ui.popular.PopularAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_playing.view.*

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.PlayingViewHolder>() {

    var clickListener: ClickListener? = null


    private var playingList: List<ResultsItemNowPlaying> = ArrayList()

   inner class PlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

       init {
           itemView.setOnClickListener(this)
       }

       lateinit var play: ResultsItemNowPlaying

        fun bindPlay(play: ResultsItemNowPlaying) {
            this.play=play
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + play.posterPath)
                .into(itemView.imgPlayingMovie)
            itemView.txtPlayingMovieName.text = play.title
        }

       override fun onClick(v: View?) {
           clickListener?.onClick(play)
       }


   }

    fun resultPlay(playList: List<ResultsItemNowPlaying>) {
        this.playingList = playList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_playing, parent, false)
        return PlayingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playingList.size
    }

    override fun onBindViewHolder(holder: PlayingViewHolder, position: Int) {
        holder.bindPlay(playingList[position])
    }

    interface ClickListener {
        fun onClick(play: ResultsItemNowPlaying)
    }

    fun setOnClickListener (clickListener: ClickListener){
        this.clickListener = clickListener
    }
}