package com.paulik.filmsappapi.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulik.filmsappapi.domain.entity.MovieDto
import com.paulik.filmsappapi.domain.repo.MovieDtoRepo
import com.paulik.filmsappapi.ui.utils.mutable

class DetailsViewModel(
    private val movieDtoRepo: MovieDtoRepo,
    private val videoId: String
) : ViewModel() {

    class Factory(private val movieDtoRepo: MovieDtoRepo, private val videoId: String) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(movieDtoRepo, videoId) as T
        }
    }

    val videoLiveData: LiveData<MovieDto> = MutableLiveData()

    init {
        //проверяе на наличие данных в videoLiveData.
        // Это необходимо для того чтобы при повороте данные не закачивались заново
        if (videoLiveData.value == null) {
            movieDtoRepo.getMovie(videoId) {
                it.let {
                    videoLiveData.mutable().postValue(it)
                }
            }
        }
    }
}