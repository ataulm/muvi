package com.muvi.film_detail_data

import com.muvi.film_detail.domain.FilmDetail
import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory

class AndroidFilmDetailRepository internal constructor(private val letterboxdApi: LetterboxdApi) : FilmDetailRepository {

    override suspend fun getFilmDetail(id: String): FilmDetail {
        return letterboxdApi.films().items.find { it.id == id }.let { filmSummaryModel ->
            FilmDetail(
                    filmSummaryModel!!.id,
                    filmSummaryModel.name,
                    "https://robohash.org/${filmSummaryModel.name}"
            )
        }
    }

    companion object {

        fun create(apiKey: String, apiSecret: String, enableHttpLogging: Boolean): AndroidFilmDetailRepository {
            val clock = object : Clock {
                override fun currentTimeMillis(): Long = System.currentTimeMillis()
            }
            val api = LetterboxdApiFactory(apiKey, apiSecret, clock, enableHttpLogging).remoteLetterboxdApi()
            return AndroidFilmDetailRepository(api)
        }
    }
}
