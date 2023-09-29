package com.paulik.filmsappapi.ui.video

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulik.filmsappapi.domain.entity.CollectionEntity
import com.paulik.filmsappapi.domain.entity.MovieDto

class CollectionVideoAdapter(
    private var data: List<CollectionEntity> = mutableListOf(),
    private var onVideoClickListener: (MovieDto) -> Unit = {},
) : RecyclerView.Adapter<CollectionVideoViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<CollectionEntity>) {
        data = video
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionVideoViewHolder {
        return CollectionVideoViewHolder(
            parent,
            onVideoClickListener
        )
    }

    override fun onBindViewHolder(holder: CollectionVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): CollectionEntity = data[position]

    override fun getItemCount(): Int = data.size
}