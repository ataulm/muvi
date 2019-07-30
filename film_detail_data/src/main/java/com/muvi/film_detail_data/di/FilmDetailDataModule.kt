package com.muvi.film_detail_data.di

import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.film_detail_data.AndroidFilmDetailRepository
import com.muvi.film_detail_data.FilmDetailRemote
import dagger.Module
import dagger.Provides

@Module
object FilmDetailDataModule {

    @JvmStatic
    @Provides
    fun filmRepository(filmDetailRemote: FilmDetailRemote): FilmDetailRepository {
        return AndroidFilmDetailRepository(filmDetailRemote)
    }
}