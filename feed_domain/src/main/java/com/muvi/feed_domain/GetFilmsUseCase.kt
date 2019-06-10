package com.muvi.feed_domain

class GetFilmsUseCase(
        private val filmRepository: FilmRepository
) {

    suspend operator fun invoke() = filmRepository.getFilms()
}
