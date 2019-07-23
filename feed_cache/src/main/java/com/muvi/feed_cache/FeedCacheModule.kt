package com.muvi.feed_cache

import androidx.room.Room
import com.muvi.feed_data.FeedCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "feed_database"

val feedCacheModule = module(override = true) {
    factory {
        val feedDatabase: FeedDatabase = get()
        feedDatabase.feedDao()
    }

    single {
        Room.databaseBuilder(
                androidContext().applicationContext,
                FeedDatabase::class.java,
                DATABASE_NAME
        ).build()
    }

    factory<FeedCache> {
        RoomFeedCache(feedDao = get())
    }
}
