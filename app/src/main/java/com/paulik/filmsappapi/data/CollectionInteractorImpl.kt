package com.paulik.filmsappapi.data

import android.os.Handler
import android.os.Looper
import com.paulik.filmsappapi.domain.entity.CollectionEntity
import com.paulik.filmsappapi.domain.interactor.CollectionInteractor
import com.paulik.filmsappapi.domain.repo.GenreRepo
import com.paulik.filmsappapi.domain.repo.MovieDtoRepo

class CollectionInteractorImpl(
    private val genreRepo: GenreRepo,
    private val movieDtoRepo: MovieDtoRepo
) : CollectionInteractor {

    override fun getCollections(callback: (List<CollectionEntity>) -> Unit) {
        val genres = genreRepo.getGenres()
        val hashMapMovies = HashMap<String, CollectionEntity>()

        Handler(Looper.getMainLooper()).postDelayed({

            movieDtoRepo.getMovies(genres) { movies ->
                movies.forEach { film ->
                    //проходим по жанрам
                    film.genreList.forEach { genre ->
                        // Кладем жанры в collection
                        val collection = hashMapMovies[genre.genre]
                            //если collection не равен нул, кладем в жанр (раздел) фильм
                            ?.apply {
                                this.movies.add(film)
                                //Если равен нул, создаем новый жанр (раздел)
                            } ?: CollectionEntity(genre, mutableListOf(film))
                        //получаем коллекцию и кладем ее обратно
                        hashMapMovies[genre.genre] = collection
                    }
                }
                callback(hashMapMovies.values.toList())
            }
        }, 1_000)
    }
}