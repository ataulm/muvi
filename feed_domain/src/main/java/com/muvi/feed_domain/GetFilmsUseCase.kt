package com.muvi.feed_domain

import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
        private val filmRepository: FilmRepository
) {

    suspend operator fun invoke() = filmRepository.getFilms()
}
