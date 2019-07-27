package com.muvi.feed_cache.di

import android.content.Context
import androidx.room.Room
import com.muvi.feed_cache.FeedDatabase
import com.muvi.feed_cache.RoomFeedCache
import com.muvi.feed_data.FeedCache
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "feed_database"

@Module
object FeedCacheModule {

    @JvmStatic
    @Provides
    fun feedCache(context: Context): FeedCache {
        // TODO: this database should be singleton in the app
        // we could manage the singleton ourselves, but cooler if we can do with dagger
        val database = Room.databaseBuilder(
                context,
                FeedDatabase::class.java,
                DATABASE_NAME
        ).build()
        val feedDao = database.feedDao()
        return RoomFeedCache(feedDao)
    }
}
