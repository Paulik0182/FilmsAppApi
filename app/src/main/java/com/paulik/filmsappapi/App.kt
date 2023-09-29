package com.paulik.filmsappapi

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import com.paulik.filmsappapi.data.CollectionInteractorImpl
import com.paulik.filmsappapi.data.retrofit.RetrofitMovieDtoRepoImpl
import com.paulik.filmsappapi.domain.interactor.CollectionInteractor
import com.paulik.filmsappapi.domain.repo.MovieDtoRepo
import com.paulik.filmsappapi.ui.utils.MyDiy

private const val API_KEY = "k_r6gwl7te" //todo должен быть в gradle secret properties

class App : Application() {

    private val myDiy: MyDiy = MyDiy()

    val movieDtoRepo: MovieDtoRepo by lazy {
        RetrofitMovieDtoRepoImpl(myDiy.imdbApi, API_KEY, this)
    }

    val collectionInteractor: CollectionInteractor by lazy {
        CollectionInteractorImpl(
            myDiy.genreRepo,
            movieDtoRepo
        )
    }

    override fun onCreate() {
        super.onCreate()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(myDiy.myReceiver, it)
        }

        registerReceiver(
            myDiy.myReceiver, IntentFilter(
                Intent.ACTION_BATTERY_LOW
            )
        )
    }
}