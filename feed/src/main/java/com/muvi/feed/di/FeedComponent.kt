package com.muvi.feed.di

import com.muvi.core.di.BaseComponent
import com.muvi.core.di.CoreComponent
import com.muvi.core.di.scope.FragmentScope
import com.muvi.feed.FeedFragment
import com.muvi.feed_domain.FilmRepository
import com.muvi.feed_remote.FeedRemoteModule
import dagger.Component

@FragmentScope
@Component(
    modules = [FeedModule::class, FeedRemoteModule::class],
    dependencies = [CoreComponent::class, FeedDatabaseComponent::class]
)
internal interface FeedComponent : BaseComponent<FeedFragment> {

    @Component.Builder
    interface Builder {
        fun build(): FeedComponent
        fun feedDatabaseComponent(feedDatabaseComponent: FeedDatabaseComponent): Builder
        fun feedRemoteModule(feedRemoteModule: FeedRemoteModule): Builder
        fun coreComponent(module: CoreComponent): Builder
    }
}