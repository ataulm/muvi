package com.muvi.film_detail.domain

interface FilmDetailRepository {

    suspend fun getFilmDetail(id: String): FilmDetail

}
