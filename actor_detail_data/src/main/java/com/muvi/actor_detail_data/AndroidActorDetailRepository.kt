package com.muvi.actor_detail_data

import com.muvi.actor_detail_domain.ActorDetail
import com.muvi.actor_detail_domain.ActorDetailRepository
import com.muvi.actor_detail_domain.CharacterInFilm
import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.remote.Clock
import com.muvi.remote.ImageModel
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory

class AndroidActorDetailRepository internal constructor(private val letterboxdApi: LetterboxdApi) : ActorDetailRepository {

    override suspend fun getFilmsByActor(id: String): ActorDetail {
        val filmContributions = letterboxdApi.filmsByActor(id).items
        return ActorDetail(filmContributions.map { filmContributionModel ->
            CharacterInFilm(
                filmContributionModel.characterName,
                FilmSummary(
                        id = filmContributionModel.film.id,
                        title = filmContributionModel.film.name,
                        year = filmContributionModel.film.releaseYear,
                        directors = filmContributionModel.film.directors.map { director -> director.name },
                        poster = filmContributionModel.film.poster?.image()
                )
        ) })
    }

    private fun ImageModel.image() = sizes
            .map { sizeModel -> Image.Size(sizeModel.width, sizeModel.height, sizeModel.url) }
            .let { sizes -> Image(sizes) }

    companion object {

        fun create(apiKey: String, apiSecret: String, enableHttpLogging: Boolean): AndroidActorDetailRepository {
            val clock = object : Clock {
                override fun currentTimeMillis(): Long = System.currentTimeMillis()
            }
            val api = LetterboxdApiFactory(apiKey, apiSecret, clock, enableHttpLogging).remoteLetterboxdApi()
            return AndroidActorDetailRepository(api)
        }
    }
}
