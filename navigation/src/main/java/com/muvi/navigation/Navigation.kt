package com.muvi.navigation

import android.content.Intent

private const val PACKAGE_NAME = "com.muvi"
private const val EXTRA_FILM_ID = "FILM_ID"
private const val EXTRA_ACTOR_ID = "ACTOR_ID"

fun filmDetailIntent(filmId: String): Intent {
    return Intent(Intent.ACTION_VIEW)
            .setClassName(PACKAGE_NAME, "$PACKAGE_NAME.film_detail.FilmDetailActivity")
            .putExtra(EXTRA_FILM_ID, filmId)
}

fun Intent.extractFilmId(): String = getStringExtra(EXTRA_FILM_ID)

fun actorDetailIntent(actorId: String): Intent {
    return Intent(Intent.ACTION_VIEW)
            .setClassName(PACKAGE_NAME, "$PACKAGE_NAME.actor_detail.ActorDetailActivity")
            .putExtra(EXTRA_ACTOR_ID, actorId)
}

fun Intent.extractActorId(): String = getStringExtra(EXTRA_ACTOR_ID)

fun themePlaygroundIntent(): Intent {
    return Intent(Intent.ACTION_VIEW)
            .setClassName(PACKAGE_NAME, "$PACKAGE_NAME.design_library.ThemePlaygroundActivity")
}
