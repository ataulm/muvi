package com.muvi.film_detail.di

import com.muvi.core.di.coreComponent
import com.muvi.film_detail.FilmDetailActivity

internal fun inject(activity: FilmDetailActivity) {
    DaggerFilmDetailComponent.builder()
            .coreComponent(activity.coreComponent())
            .activity(activity)
            .build()
            .inject(activity)
}
