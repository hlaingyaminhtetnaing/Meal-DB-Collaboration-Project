package com.hlaingyaminhtetnaing.mealdbproject.ui.search
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlaingyaminhtetnaing.mealdbproject.R
import com.hlaingyaminhtetnaing.mealdbproject.model.ResultsItemSearch
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search.view.*
import java.lang.reflect.Array

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchList: List<ResultsItemSearch> = ArrayList()
    var clickListener: SearchAdapter.ClickListener? = null

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        lateinit var play: ResultsItemSearch

        fun bindPlay(play: ResultsItemSearch) {
                this.play=play
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + play.posterPath)
                .placeholder(R.drawable.ic_menu_gallery)
                .into(itemView.imgSearch)
            itemView.txtSearch.text = play.title
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(play)
        }

    }

    fun resultPlay(searchList: List<ResultsItemSearch>) {
        this.searchList = searchList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindPlay(searchList[position])
    }
    fun setOnClickListener(clickListener: SearchFragment){
        this.clickListener=clickListener
    }
    interface ClickListener {
        fun onClick(play: ResultsItemSearch)
    }

    fun clearData () {
        searchList = ArrayList()
        notifyDataSetChanged()
    }
}