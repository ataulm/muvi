package com.muvi.film_detail.domain

class GetFilmDetailUseCase(
        private val filmDetailRepository: FilmDetailRepository
) {

    suspend operator fun invoke(id: String) = filmDetailRepository.getFilm(id)
}
