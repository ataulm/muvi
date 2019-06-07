package com.muvi.feed_domain

interface FilmRepository {

    suspend fun getFilms(): List<Film>

}
