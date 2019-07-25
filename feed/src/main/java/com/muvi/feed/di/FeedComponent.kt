package com.muvi.feed.di

import com.muvi.core.di.BaseComponent
import com.muvi.core.di.CoreComponent
import com.muvi.feed.FeedFragment
import com.muvi.feed_cache.FeedCacheModule
import com.muvi.feed_remote.FeedRemoteModule
import dagger.Component

@Component(
    modules = [FeedModule::class, FeedRemoteModule::class, FeedCacheModule::class],
    dependencies = [CoreComponent::class]
)
internal interface FeedComponent : BaseComponent<FeedFragment> {

    @Component.Builder
    interface Builder {
        fun build(): FeedComponent
        fun feedRemoteModule(feedRemoteModule: FeedRemoteModule): Builder
        fun feedCacheModule(feedCacheModule: FeedCacheModule): Builder
        fun coreComponent(module: CoreComponent): Builder
    }
}