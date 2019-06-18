package com.muvi.actor_detail_domain

interface ActorDetailRepository {

    suspend fun getFilmsByActor(id: String): ActorDetail

}
