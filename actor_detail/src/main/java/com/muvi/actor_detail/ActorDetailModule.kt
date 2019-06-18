package com.muvi.actor_detail

import com.muvi.BuildConfig
import com.muvi.actor_detail_data.AndroidActorDetailRepository
import com.muvi.actor_detail_domain.ActorDetailRepository
import com.muvi.actor_detail_domain.GetActorDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val actorDetailModule = module {

    viewModel { (actorId: String) -> ActorDetailViewModel(actorId, get()) }

    factory<ActorDetailRepository> {
        AndroidActorDetailRepository.create(
                BuildConfig.API_KEY,
                BuildConfig.API_SECRET,
                enableHttpLogging = BuildConfig.DEBUG
        )
    }

    factory {
        GetActorDetailUseCase(get())
    }
}
