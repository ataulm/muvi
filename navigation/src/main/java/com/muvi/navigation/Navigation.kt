package com.muvi.navigation

import android.content.Intent

fun filmDetailIntent(filmId: String): Intent {
    return Intent(Intent.ACTION_VIEW)
            .setClassName("com.muvi", "com.muvi.film_detail.FilmDetailActivity")
            .putExtra("FILM_ID", filmId)
}
