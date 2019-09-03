package com.marsroverphotos.ui.marsPhoto

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.mars_photo_item.view.*
import com.marsroverphotos.R
import com.module.domain.entities.MarsPhotoEntity

class MarsPhotoAdapter : RecyclerView.Adapter<MarsPhotoAdapter.MarsPhotoViewHolder>() {

    var photos = mutableListOf<MarsPhotoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mars_photo_item, parent, false)
        return MarsPhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    class MarsPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photoItem: MarsPhotoEntity) {
            with(itemView) {
                Glide
                    .with(photo_view.context)
                    .load(photoItem.url)
                    .into(photo_view)

            }
        }
    }

    fun updateList(list: List<MarsPhotoEntity>) {
        if (list.isNotEmpty()) {
            photos.clear()
            photos.addAll(list)
            notifyDataSetChanged()
        }
    }
}