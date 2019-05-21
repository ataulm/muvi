package com.tree.nuts

class AndroidFilmRepository internal constructor(private val letterboxdApi: LetterboxdApi) : FilmRepository {

    override suspend fun getFilms(): List<Film> {
        return letterboxdApi.films().await().let { filmsResponseModel ->
            filmsResponseModel.items.map { Film(it.name) }
        }
    }

    companion object {

        fun create(apiKey: String, apiSecret: String, clock: Clock): AndroidFilmRepository {
            val api = LetterboxdApiFactory(apiKey, apiSecret, clock).remoteLetterboxdApi()
            return AndroidFilmRepository(api)
        }
    }
}
