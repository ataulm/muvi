package com.muvi.actor_detail_data

import com.muvi.actor_detail_domain.ActorDetailRepository
import org.koin.dsl.module

val actorDetailDataModule = module(override = true) {

    factory<ActorDetailRepository> {
        RemoteActorDetailRepository(
                actorDetailRemote = get()
        )
    }
}
