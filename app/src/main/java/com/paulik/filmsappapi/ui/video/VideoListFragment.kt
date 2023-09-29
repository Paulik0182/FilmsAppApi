package com.paulik.filmsappapi.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.filmsappapi.App
import com.paulik.filmsappapi.MyReceiver
import com.paulik.filmsappapi.databinding.FragmentVideoListBinding
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.paulik.filmsappapi.domain.interactor.CollectionInteractor
import com.paulik.filmsappapi.ui.ViewBindingFragment
import java.util.UUID

private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class VideoListFragment : ViewBindingFragment<FragmentVideoListBinding>(
    FragmentVideoListBinding::inflate
) {

    private val app: App get() = requireActivity().application as App

    private val collectionVideoRepo: CollectionInteractor by lazy {
        app.collectionInteractor
    }

    private val viewModel: VideoListViewModel by lazy {
        ViewModelProvider(
            this,
            VideoListViewModel.Factory(
                collectionVideoRepo
            )
        )[VideoListViewModel::class.java]
    }

    private lateinit var adapter: CollectionVideoAdapter

    //уникальный id (для сохранения состояние экрана за пределами класса)
    private lateinit var fragmentUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentUid =
            savedInstanceState?.getString(FRAGMENT_UUID_KEY) ?: UUID.randomUUID().toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FRAGMENT_UUID_KEY, fragmentUid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            binding.collectionVideoRecyclerView.isVisible = !inProgress
            binding.progressTaskBar.isVisible = inProgress
        }

        viewModel.videoListLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.selectedVideoLiveData.observe(viewLifecycleOwner) {
            getController().openDetailsVideo(it)
        }

        onReceiver()
    }

    private fun onReceiver() {
        context?.let {
            it.sendBroadcast(Intent(it, MyReceiver::class.java).apply {
                putExtra(
                    "Action",
                    "Запущен фрагмент Видео (Receiver)"
                )
            })
        }
    }

    private fun initView() {
        binding.collectionVideoRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CollectionVideoAdapter(
            data = emptyList(),
            onVideoClickListener = {
                viewModel.onVideoClick(it)
            }
        )
        binding.collectionVideoRecyclerView.adapter = adapter
    }

    interface Controller {
        fun openDetailsVideo(movieDto: MovieDto)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}