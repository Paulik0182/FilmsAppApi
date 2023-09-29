package com.paulik.filmsappapi.domain.interactor

import com.paulik.filmsappapi.domain.entity.CollectionEntity

interface CollectionInteractor {

    fun getCollections(callback: (List<CollectionEntity>) -> Unit)
}