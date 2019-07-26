package com.muvi.feed_cache.di

import android.content.Context
import androidx.room.Room
import com.muvi.feed_cache.FeedDao
import com.muvi.feed_cache.FeedDatabase
import com.muvi.feed_cache.RoomFeedCache
import com.muvi.feed_data.FeedCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "feed_database"

@Module
class FeedCacheModule {

    @Provides
    internal fun providesFeedDao(feedDatabase: FeedDatabase): FeedDao {
        return feedDatabase.feedDao()
    }

    @FeedCacheScope
    @Provides
    internal fun providesFeedDatabase(context: Context): FeedDatabase {
        return Room.databaseBuilder(
            context,
            FeedDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    internal fun providesFeedCache(feedDao: FeedDao): FeedCache {
        return RoomFeedCache(feedDao)
    }
}