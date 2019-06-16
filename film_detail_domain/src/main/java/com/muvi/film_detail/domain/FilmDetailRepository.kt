package com.muvi.film_detail.domain

import com.muvi.base_domain.Film

interface FilmDetailRepository {

    suspend fun getFilm(id: String): Film

}
