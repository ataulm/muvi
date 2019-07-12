package com.muvi.feed

import com.muvi.feed_data.AndroidFilmRepository
import com.muvi.feed_domain.FilmRepository
import com.muvi.feed_domain.GetFilmsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module(override = true) {
    viewModel { FeedViewModel(get()) }

    factory<FilmRepository> {
        AndroidFilmRepository.create(
                androidContext(),
                BuildConfig.API_KEY,
                BuildConfig.API_SECRET,
                enableHttpLogging = BuildConfig.DEBUG
        )
    }

    factory {
        GetFilmsUseCase(filmRepository = get())
    }
}
