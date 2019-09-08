package com.muvi.actor_detail_remote

import com.muvi.actor_detail_data.ActorDetailRemote
import org.koin.dsl.module

val actorDetailRemoteModule = module(override = true) {

    factory<ActorDetailRemote> {
        LetterboxdActorDetailRemote(letterboxdApi = get())
    }
}
