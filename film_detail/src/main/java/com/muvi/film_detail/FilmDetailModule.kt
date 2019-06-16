package com.muvi.film_detail

import com.muvi.BuildConfig
import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.film_detail.domain.GetFilmDetailUseCase
import com.muvi.film_detail_data.AndroidFilmDetailRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmDetailModule = module {

    viewModel { (filmId: String) -> FilmDetailViewModel(filmId, get()) }

    factory<FilmDetailRepository> {
        AndroidFilmDetailRepository.create(
                BuildConfig.API_KEY,
                BuildConfig.API_SECRET,
                enableHttpLogging = BuildConfig.DEBUG
        )
    }

    factory {
        GetFilmDetailUseCase(get())
    }
}
