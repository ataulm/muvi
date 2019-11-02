package com.muvi.film_detail_remote

import com.muvi.base_domain.Actor
import com.muvi.base_domain.Character
import com.muvi.base_domain.Film
import com.muvi.base_domain.Image
import com.muvi.film_detail_data.FilmDetailRemote
import com.muvi.remote.FilmModel
import com.muvi.remote.ImageModel
import com.muvi.remote.LetterboxdApi
import javax.inject.Inject

internal class LetterboxdFilmRemote @Inject constructor(
        private val letterboxdApi: LetterboxdApi
) : FilmDetailRemote {

    override suspend fun getFilm(id: String): Film {
        return letterboxdApi.film(id).let { filmModel ->
            Film(
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
    }

    private fun FilmModel.directors(): List<String> {
        return contributions.filter { contribution -> contribution.type == "Director" }
                .flatMap { contribution -> contribution.contributors }
                .map { contributor -> contributor.name }

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

}
