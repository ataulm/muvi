package com.muvi.actor_detail_remote

import com.muvi.actor_detail_data.ActorDetailRemote
import com.muvi.actor_detail_domain.ActorDetail
import com.muvi.actor_detail_domain.CharacterInFilm
import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.remote.ImageModel
import com.muvi.remote.LetterboxdApi

internal class LetterboxdActorDetailRemote(
        private val letterboxdApi: LetterboxdApi
) : ActorDetailRemote {

    override suspend fun getActorDetail(id: String): ActorDetail {
        val filmContributions = letterboxdApi.filmsByActor(id).items
        return ActorDetail(
                films = filmContributions.map { filmContributionModel ->
                    CharacterInFilm(
                            filmContributionModel.characterName?.let { if (it.isEmpty()) null else it },
                            FilmSummary(
                                    id = filmContributionModel.film.id,
                                    title = filmContributionModel.film.name,
                                    year = filmContributionModel.film.releaseYear,
                                    directors = filmContributionModel.film.directors.map { director -> director.name },
                                    poster = filmContributionModel.film.poster?.image()
                            )
                    )
                })
    }

    private fun ImageModel.image() = sizes
            .map { sizeModel -> Image.Size(sizeModel.width, sizeModel.height, sizeModel.url) }
            .let { sizes -> Image(sizes) }
}
