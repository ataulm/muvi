package com.muvi.feed_remote.di

import com.muvi.feed_data.FeedRemote
import com.muvi.feed_remote.LetterboxdFeedRemote
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
object FeedRemoteModule {

    @JvmStatic
    @Provides
    fun feedRemote(
            @LetterboxdApiKey apiKey: String,
            @LetterboxdApiSecret apiSecret: String,
            @DebugMode debugMode: Boolean
    ): FeedRemote = LetterboxdFeedRemote(letterboxdApi(apiKey, apiSecret, debugMode))

    private fun clock(): Clock = object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    }

    // TODO: we could break this up better - e.g. share OkHttpClient instance across app
    // that would give us two good scopes to showcase - per feature (e.g. RoomFeedDB) and per app
    private fun letterboxdApi(apiKey: String, apiSecret: String, debugMode: Boolean): LetterboxdApi = LetterboxdApiFactory(
            apiKey,
            apiSecret,
            clock(),
            debugMode
    ).remoteLetterboxdApi()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LetterboxdApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LetterboxdApiSecret

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugMode
