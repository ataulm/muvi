package com.muvi.film_detail.di

import com.muvi.core.di.appComponent
import com.muvi.film_detail.FilmDetailActivity

internal fun inject(activity: FilmDetailActivity, filmId: String) {
    DaggerFilmDetailComponent.builder()
            .coreComponent(activity.appComponent())
            .activity(activity)
            .with(filmId)
            .build()
            .inject(activity)
}
