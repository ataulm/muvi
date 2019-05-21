package com.tree.nuts

class GetFilmsUseCase(
        private val filmRepository: FilmRepository
) {

    suspend operator fun invoke() = filmRepository.getFilms()
}
