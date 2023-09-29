package com.paulik.filmsappapi.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionEntity(

    val genre: GenreDto,

    val movies: MutableList<MovieDto> = mutableListOf()

) : Parcelable
