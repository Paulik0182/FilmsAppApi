package com.paulik.filmsappapi.data

import com.paulik.filmsappapi.domain.repo.GenreRepo

/**
 * список разделов
 */

class GenreRepoImpl : GenreRepo {

    override fun getGenres(): List<String> = mutableListOf(
        "action",
        "comedy",
        "family",
        "history"
    )
}