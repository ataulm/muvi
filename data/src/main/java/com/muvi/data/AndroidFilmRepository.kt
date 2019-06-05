package com.muvi.data

import com.muvi.domain.Film
import com.muvi.domain.FilmRepository

class AndroidFilmRepository internal constructor(private val letterboxdApi: LetterboxdApi) : FilmRepository {

    override suspend fun getFilms(): List<Film> {
        return letterboxdApi.films().await().let { filmsResponseModel ->
            filmsResponseModel.items.map { Film(it.id, it.name) }
        }
    }

    companion object {

        fun create(apiKey: String, apiSecret: String, clock: Clock): AndroidFilmRepository {
            val api = LetterboxdApiFactory(apiKey, apiSecret, clock).remoteLetterboxdApi()
            return AndroidFilmRepository(api)
        }
    }
}
