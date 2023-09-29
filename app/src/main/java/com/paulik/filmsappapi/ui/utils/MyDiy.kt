package com.paulik.filmsappapi.ui.utils

import com.paulik.filmsappapi.MyReceiver
import com.paulik.filmsappapi.data.GenreRepoImpl
import com.paulik.filmsappapi.data.retrofit.ImdbApi
import com.paulik.filmsappapi.domain.repo.GenreRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://imdb-api.com/ru/API/"


class MyDiy {

    val myReceiver: MyReceiver by lazy { MyReceiver() }

    val genreRepo: GenreRepo by lazy { GenreRepoImpl() }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val imdbApi: ImdbApi by lazy { retrofit.create(ImdbApi::class.java) }
}