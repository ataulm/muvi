package com.muvi.film_detail_data

import com.muvi.base_domain.Actor
import com.muvi.base_domain.Character
import com.muvi.base_domain.Film
import com.muvi.base_domain.Image
import com.muvi.film_detail.domain.FilmDetailRepository
import com.muvi.remote.Clock
import com.muvi.remote.FilmModel
import com.muvi.remote.ImageModel
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory

class AndroidFilmDetailRepository internal constructor(private val letterboxdApi: LetterboxdApi) : FilmDetailRepository {

    override suspend fun getFilm(id: String): Film {
        val filmModel = letterboxdApi.film(id)
        return Film(
                id = filmModel.id,
                title = filmModel.name,
                year = filmModel.releaseYear,
                directors = filmModel.directors(),
                poster = filmModel.poster?.image(),
                description = filmModel.description,
                backdrop = filmModel.backdrop?.image(),
                characters = filmModel.characters()
        )
    }

    private fun FilmModel.directors(): String {
        return contributions.filter { contribution -> contribution.type == "Director" }
                .flatMap { contribution -> contribution.contributors }
                .joinToString(separator = " & ") { contributor -> contributor.name }
    }

    private fun FilmModel.characters(): List<Character> {
        return contributions.filter { contribution -> contribution.type == "Actor" }
                .flatMap { contribution -> contribution.contributors }
                .map { contributor ->
                    val actor = Actor(contributor.id, contributor.name)
                    Character(contributor.characterName!!, actor)
                }
    }

    private fun ImageModel.image() = sizes
            .map { sizeModel -> Image.Size(sizeModel.width, sizeModel.height, sizeModel.url) }
            .let { sizes -> Image(sizes) }

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
