package com.muvi.actor_detail

import com.muvi.actor_detail_domain.GetActorDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val actorDetailModule = module(override = true) {

    viewModel { (actorId: String) -> ActorDetailViewModel(actorId, get()) }

    factory {
        GetActorDetailUseCase(actorDetailRepository = get())
    }
}
