package com.paulik.filmsappapi.domain.repo

import com.paulik.filmsappapi.domain.entity.MovieDto

interface MovieDtoRepo {

    fun getMovies(genres: List<String>, callback: (List<MovieDto>) -> Unit)
    fun getMovie(id: String, callback: (MovieDto?) -> Unit)
}