package com.muvi.feed.di

import com.muvi.core.di.BaseComponent
import com.muvi.core.di.CoreComponent
import com.muvi.feed.FeedFragment
import com.muvi.feed_cache.di.FeedCacheModule
import com.muvi.feed_cache.di.FeedCacheScope
import com.muvi.feed_data.FeedCache
import dagger.Component
import javax.inject.Singleton

@FeedCacheScope
@Component(
    modules = [FeedCacheModule::class],
    dependencies = [CoreComponent::class]
)
internal interface FeedDatabaseComponent : BaseComponent<FeedFragment> {

    @Component.Builder
    interface Builder {
        fun build(): FeedDatabaseComponent
        fun feedCacheModule(feedCacheModule: FeedCacheModule): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

    fun feedCache(): FeedCache
}