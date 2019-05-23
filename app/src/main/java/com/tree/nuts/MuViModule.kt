package com.tree.nuts

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val muviModule = module {

    viewModel { FeedViewModel(get()) }

    factory<FilmRepository> {
        AndroidFilmRepository.create(BuildConfig.API_KEY,
            BuildConfig.API_SECRET, get())
    }

    factory {
        GetFilmsUseCase(get())
    }

    factory<Clock> {
        object : Clock {
            override fun currentTimeMillis(): Long = System.currentTimeMillis()
        }
    }
}
