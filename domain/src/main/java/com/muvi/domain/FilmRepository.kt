package com.muvi.domain

interface FilmRepository {

    suspend fun getFilms(): List<Film>

}
