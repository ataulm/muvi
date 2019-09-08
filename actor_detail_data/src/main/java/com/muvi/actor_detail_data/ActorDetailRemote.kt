package com.muvi.actor_detail_data

import com.muvi.actor_detail_domain.ActorDetail

interface ActorDetailRemote {

    suspend fun getActorDetail(id: String): ActorDetail
}
