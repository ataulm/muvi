package com.muvi.feed.di

import com.muvi.core.di.BaseComponent
import com.muvi.core.di.CoreComponent
import com.muvi.feed.FeedFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [FeedModule::class],
    dependencies = [CoreComponent::class]
)
internal interface FeedComponent : BaseComponent<FeedFragment> {

    @Component.Builder
    interface Builder {
        fun build(): FeedComponent
        fun coreComponent(module: CoreComponent): Builder
    }
}