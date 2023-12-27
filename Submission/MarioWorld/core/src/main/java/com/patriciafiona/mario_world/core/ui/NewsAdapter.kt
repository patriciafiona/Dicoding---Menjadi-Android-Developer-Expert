package com.patriciafiona.mario_world.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.patriciafiona.mario_world.core.R
import com.patriciafiona.mario_world.core.domain.model.News

class NewsAdapter(private val listNews: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, title, headline, link, date, image) = listNews[position]
        holder.tvTitle.text = title
        holder.tvDate.text = date
        holder.tvDescription.text = headline
        holder.ivBanner.setImageResource(image)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(link) }
    }

    override fun getItemCount(): Int = listNews.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBanner: ImageView = itemView.findViewById(R.id.iv_banner_image)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_news_title)
        val tvDate: TextView = itemView.findViewById(R.id.tv_news_date)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_news_desc)
    }

    interface OnItemClickCallback {
        fun onItemClicked(link: String)
    }
}