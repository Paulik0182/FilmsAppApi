package com.paulik.filmsappapi.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.paulik.filmsappapi.R
import com.paulik.filmsappapi.databinding.ItemVideoListBinding
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.squareup.picasso.Picasso

class VideoListViewHolder(
    parent: ViewGroup,
    onDetailVideoListener: (MovieDto) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_video_list, parent, false)
) {

    private val binding: ItemVideoListBinding = ItemVideoListBinding.bind(itemView)

    private lateinit var video: MovieDto

    fun bind(movieDto: MovieDto) {
        this.video = movieDto

        binding.nameTextView.text = movieDto.title
        binding.yearReleaseTextView.text = movieDto.yearRelease
        if (movieDto.image.isNotBlank()) {
            Picasso.get()
                .load(movieDto.image)
                .placeholder(R.drawable.uploading_images)
                .into(binding.coverImageView)
            binding.coverImageView.scaleType =
                ImageView.ScaleType.FIT_XY
        }
    }

    init {
        itemView.setOnClickListener {
            onDetailVideoListener.invoke(video)
        }
    }
}