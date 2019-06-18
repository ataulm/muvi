package com.muvi.actor_detail_domain

import com.muvi.base_domain.FilmSummary

data class ActorDetail(val films: List<CharacterInFilm>)

data class CharacterInFilm(
        val characterName: String?,
        val filmSummary: FilmSummary
)
