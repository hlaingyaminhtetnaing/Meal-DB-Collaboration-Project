package com.hlaingyaminhtetnaing.mealdbproject.ui.popular
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemPopular
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    var clickListener: ClickListener? = null

    fun setOnClickListener (clickListener: ClickListener){
        this.clickListener = clickListener
    }

    private var popularList: List<ResultsItemPopular> = ArrayList()


    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        lateinit var play: ResultsItemPopular
        fun bindPlay(play: ResultsItemPopular) {
            this.play=play
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + play.posterPath)
                .into(itemView.imgPopular)
            itemView.txtPopularName.text = play.title
        }

        override fun onClick(p0: View?) {
            clickListener?.onClick(play)
        }
    }

    fun resultPlay(popularList: List<ResultsItemPopular>) {
        this.popularList = popularList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bindPlay(popularList[position])
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    interface ClickListener {
        fun onClick(play: ResultsItemPopular)
    }

}