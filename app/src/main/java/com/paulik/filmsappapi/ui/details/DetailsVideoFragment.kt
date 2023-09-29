package com.paulik.filmsappapi.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.paulik.filmsappapi.App
import com.paulik.filmsappapi.R
import com.paulik.filmsappapi.databinding.FragmentDetailsVideoBinding
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.paulik.filmsappapi.domain.repo.MovieDtoRepo
import com.paulik.filmsappapi.ui.ViewBindingFragment
import com.paulik.filmsappapi.ui.utils.snack
import com.squareup.picasso.Picasso
import java.util.UUID

private const val DETAILS_VIDEO_KEY = "DETAILS_VIDEO_KEY"
private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class DetailsVideoFragment : ViewBindingFragment<FragmentDetailsVideoBinding>(
    FragmentDetailsVideoBinding::inflate
) {

    private val app: App get() = requireActivity().application as App

    private val videoRepo: MovieDtoRepo by lazy {
        app.movieDtoRepo
    }

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Factory(videoRepo, requireArguments().getString(DETAILS_VIDEO_KEY)!!)
    }

    //уникальный id (для сохранения состояние экрана за пределами класса)
    private lateinit var fragmentUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentUid =
            savedInstanceState?.getString(FRAGMENT_UUID_KEY) ?: UUID.randomUUID().toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //при сохроанении положить ID
        outState.putString(FRAGMENT_UUID_KEY, fragmentUid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.videoLiveData.observe(viewLifecycleOwner) {
            setVideoEntity(it)

            view.snack(getString(R.string.name_film) + it.title)
        }
    }

    private fun setVideoEntity(movieDto: MovieDto) {
        binding.nameDetailsTextView.text = movieDto.title
        binding.genreDetailsTextView.text = movieDto.genres
        binding.yearReleaseDetailsTextView.text = movieDto.yearRelease
        binding.descriptionDetailsTextView.text = movieDto.description

        if (movieDto.image.isNotBlank()) {
            //Picasso
            Picasso.get()
                .load(movieDto.image)
                .placeholder(R.drawable.uploading_images)
                .into(binding.coverImageView)
            binding.coverImageView.scaleType =
                ImageView.ScaleType.FIT_CENTER
        }
    }

    interface Controller {
        // TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        fun newInstance(videoId: String) =
            DetailsVideoFragment().apply {
                arguments = Bundle().apply {
                    putString(DETAILS_VIDEO_KEY, videoId)
                }
            }
    }
}