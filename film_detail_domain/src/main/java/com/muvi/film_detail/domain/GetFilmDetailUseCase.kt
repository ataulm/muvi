package com.muvi.film_detail.domain

import javax.inject.Inject

class GetFilmDetailUseCase @Inject constructor(
        private val filmDetailRepository: FilmDetailRepository
) {

    suspend operator fun invoke(id: String) = filmDetailRepository.getFilm(id)
}
