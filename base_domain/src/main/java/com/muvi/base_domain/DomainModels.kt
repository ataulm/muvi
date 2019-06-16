package com.muvi.base_domain

data class FilmSummary(
        val id: String,
        val title: String,
        val year: Int,
        val directors: String?,
        val poster: Image?
)

data class Film(
        val id: String,
        val title: String,
        val year: Int,
        val directors: String,
        val poster: Image?,
        val backdrop: Image?,
        val description: String?,
        val cast: List<Character>
)

data class Character(val name: String, val actor: Actor)

data class Actor(val id: String, val name: String)

data class Image(val sizes: List<Size>) {

    data class Size(val width: Int, val height: Int, val url: String)
}
