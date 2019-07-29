package com.muvi.feed_data.di

import com.muvi.feed_data.CacheAndRemoteFilmRepository
import com.muvi.feed_data.FeedCache
import com.muvi.feed_data.FeedRemote
import com.muvi.feed_domain.FilmRepository
import dagger.Module
import dagger.Provides

@Module
object FeedDataModule {

    @JvmStatic
    @Provides
    fun filmRepository(feedRemote: FeedRemote, feedCache: FeedCache): FilmRepository {
        return CacheAndRemoteFilmRepository(feedRemote, feedCache)
    }
}
