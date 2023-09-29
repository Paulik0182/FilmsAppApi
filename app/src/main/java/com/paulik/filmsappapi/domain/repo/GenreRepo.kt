package com.paulik.filmsappapi.domain.repo

interface GenreRepo {
    fun getGenres(): List<String>
}