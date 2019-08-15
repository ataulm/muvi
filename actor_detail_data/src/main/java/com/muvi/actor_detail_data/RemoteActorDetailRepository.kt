package com.muvi.actor_detail_data

import com.muvi.actor_detail_domain.ActorDetail
import com.muvi.actor_detail_domain.ActorDetailRepository

internal class RemoteActorDetailRepository(
        private val actorDetailRemote: ActorDetailRemote
) : ActorDetailRepository {

    override suspend fun getFilmsByActor(id: String): ActorDetail {
        return actorDetailRemote.getActorDetail(id)
    }
}
