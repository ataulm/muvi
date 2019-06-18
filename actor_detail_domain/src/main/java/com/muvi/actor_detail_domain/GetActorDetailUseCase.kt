package com.muvi.actor_detail_domain

class GetActorDetailUseCase(
        private val actorDetailRepository: ActorDetailRepository
) {

    suspend operator fun invoke(id: String) = actorDetailRepository.getFilmsByActor(id)
}
