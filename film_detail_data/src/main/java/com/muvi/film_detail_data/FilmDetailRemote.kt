package com.muvi.film_detail_data

import com.muvi.base_domain.Film

interface FilmDetailRemote {

    suspend fun getFilm(id: String): Film
}
