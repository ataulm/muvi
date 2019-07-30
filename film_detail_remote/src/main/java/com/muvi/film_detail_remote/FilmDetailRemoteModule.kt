package com.muvi.film_detail_remote

import com.muvi.film_detail_data.FilmDetailRemote
import com.muvi.remote.LetterboxdApi
import dagger.Module
import dagger.Provides

@Module
object FilmDetailRemoteModule {

    @JvmStatic
    @Provides
    fun feedRemote(
            letterboxdApi: LetterboxdApi
    ): FilmDetailRemote = LetterboxdFilmRemote(letterboxdApi)
}
