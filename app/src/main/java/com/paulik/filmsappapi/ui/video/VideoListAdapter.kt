package com.paulik.filmsappapi.ui.video

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulik.filmsappapi.domain.entity.MovieDto

class VideoListAdapter(
    private var data: List<MovieDto> = mutableListOf(),
    private var onDetailVideoListener: (MovieDto) -> Unit = {},
) : RecyclerView.Adapter<VideoListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(video: List<MovieDto>) {
        data = video
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        return VideoListViewHolder(
            parent,
            onDetailVideoListener
        )
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): MovieDto = data[position]

    override fun getItemCount(): Int = data.size
}