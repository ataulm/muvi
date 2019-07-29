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

    @Volatile
    private var feedDatabase: FeedDatabase? = null

    @JvmStatic
    @Provides
    fun feedCache(context: Context): FeedCache {
        val database = feedDatabase(context)
        val feedDao = database.feedDao()
        return RoomFeedCache(feedDao)
    }

    private fun feedDatabase(context: Context): FeedDatabase {
        return feedDatabase ?: synchronized(this) {
            feedDatabase ?: Room.databaseBuilder(
                    context,
                    FeedDatabase::class.java,
                    DATABASE_NAME
            ).build().also { feedDatabase = it }
        }
    }
}
