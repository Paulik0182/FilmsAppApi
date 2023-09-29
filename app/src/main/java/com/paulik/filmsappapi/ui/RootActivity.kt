package com.paulik.filmsappapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.paulik.filmsappapi.R
import com.paulik.filmsappapi.databinding.ActivityRootBinding
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.paulik.filmsappapi.ui.details.DetailsVideoFragment
import com.paulik.filmsappapi.ui.video.VideoListFragment

private const val TAG_DETAILS_VIDEO_KEY = "TAG_DETAILS_VIDEO_KEY"

class RootActivity : AppCompatActivity(),
    VideoListFragment.Controller,
    DetailsVideoFragment.Controller {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_layout, VideoListFragment())
                .commit()

    }

    private fun openDetailsVideoFragment(videoId: String) {
        val fragment: Fragment = DetailsVideoFragment.newInstance(videoId)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, fragment, TAG_DETAILS_VIDEO_KEY)
            .addToBackStack(null)
            .commit()
    }

    override fun openDetailsVideo(movieDto: MovieDto) {
        openDetailsVideoFragment(movieDto.id)
    }
}