package com.muvi.feed_domain

import com.muvi.base_domain.FilmSummary

interface FilmRepository {

    suspend fun getFilms(): List<FilmSummary>

}
