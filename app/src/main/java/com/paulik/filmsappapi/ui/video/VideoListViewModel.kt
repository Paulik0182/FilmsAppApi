package com.paulik.filmsappapi.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulik.filmsappapi.domain.entity.CollectionEntity
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.paulik.filmsappapi.domain.interactor.CollectionInteractor
import com.paulik.filmsappapi.ui.utils.mutable

class VideoListViewModel(
    private val collectionVideoRepo: CollectionInteractor
) : ViewModel() {

    class Factory(private val collectionVideoRepo: CollectionInteractor) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VideoListViewModel(collectionVideoRepo) as T
        }
    }

    val inProgressLiveData: LiveData<Boolean> = MutableLiveData(false)
    val videoListLiveData: LiveData<List<CollectionEntity>> = MutableLiveData()
    val selectedVideoLiveData: LiveData<MovieDto> = MutableLiveData()

    init {
        inProgressLiveData.mutable().postValue(true)
        collectionVideoRepo.getCollections {
            inProgressLiveData.mutable().postValue(false)
            videoListLiveData.mutable().postValue(it)
        }
    }

    fun onVideoClick(movieDto: MovieDto) {
        selectedVideoLiveData.mutable().postValue(movieDto)
    }
}