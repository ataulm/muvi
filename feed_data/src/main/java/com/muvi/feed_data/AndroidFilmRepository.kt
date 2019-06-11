package com.muvi.feed_data

import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.feed_domain.FilmRepository
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory

class AndroidFilmRepository internal constructor(private val letterboxdApi: LetterboxdApi) : FilmRepository {

    override suspend fun getFilms(): List<FilmSummary> {
        return letterboxdApi.films().let { filmsResponseModel ->
            filmsResponseModel.items.map { filmSummaryModel ->
                FilmSummary(
                        id = filmSummaryModel.id,
                        title = filmSummaryModel.name,
                        year = filmSummaryModel.releaseYear,
                        director = filmSummaryModel.directors.firstOrNull()?.name,
                        poster = filmSummaryModel.poster?.let { imageModel ->
                            val sizes = imageModel.sizes.map { sizeModel ->
                                Image.Size(sizeModel.width, sizeModel.height, sizeModel.url)
                            }
                            Image(sizes)
                        }
                )
            }
        }
    }

    companion object {

        fun create(apiKey: String, apiSecret: String): AndroidFilmRepository {
            val clock = object : Clock {
                override fun currentTimeMillis(): Long = System.currentTimeMillis()
            }
            val api = LetterboxdApiFactory(apiKey, apiSecret, clock).remoteLetterboxdApi()
            return AndroidFilmRepository(api)
        }
    }
}
