package com.paulik.filmsappapi.ui.utils

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

fun View.snack(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.ANIMATION_MODE_SLIDE
    ).show()
}

fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
    return this as MutableLiveData
}