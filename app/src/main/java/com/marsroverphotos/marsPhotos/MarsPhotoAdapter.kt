package com.marsroverphotos.marsPhotos

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marsroverphotos.entities.MarsPhoto
import kotlinx.android.synthetic.main.mars_photo_item.view.*
import com.marsroverphotos.R

class MarsPhotoAdapter : RecyclerView.Adapter<MarsPhotoAdapter.NewsViewHolder>() {

    var articles = mutableListOf<MarsPhoto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mars_photo_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsPublisherItem: MarsPhoto) {
            with(itemView) {
                heading.text = newsPublisherItem.url
            }
        }
    }

    fun updateList(list: List<MarsPhoto>) {
        if (list.isNotEmpty()) {
            articles.clear()
            articles.addAll(list)
            notifyDataSetChanged()
        }
    }
}