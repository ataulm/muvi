package com.tree.nuts

interface FilmRepository {

    suspend fun getFilms(): List<Film>

}
