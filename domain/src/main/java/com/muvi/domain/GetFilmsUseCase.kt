package com.muvi.domain

class GetFilmsUseCase(
        private val filmRepository: FilmRepository
) {

    suspend operator fun invoke() = filmRepository.getFilms()
}
