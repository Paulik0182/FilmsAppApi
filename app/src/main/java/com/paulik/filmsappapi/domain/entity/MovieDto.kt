package com.paulik.filmsappapi.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("runtimeStr")
    val runtimeStr: String,

    @SerializedName("genres")
    val genres: String,

    @SerializedName("genreList")
    val genreList: MutableList<GenreDto>,

    @SerializedName("year")
    val yearRelease: String = "1900"

) : Parcelable
