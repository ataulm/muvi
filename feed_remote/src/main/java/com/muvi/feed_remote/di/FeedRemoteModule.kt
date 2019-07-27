package com.muvi.feed_remote.di

import com.muvi.feed_data.BuildConfig
import com.muvi.feed_data.FeedRemote
import com.muvi.feed_remote.LetterboxdFeedRemote
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory
import dagger.Module
import dagger.Provides

@Module
object FeedRemoteModule {

    @JvmStatic
    @Provides
    fun feedRemote(): FeedRemote = LetterboxdFeedRemote(letterboxdApi())

    private fun clock(): Clock = object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    }

    // TODO: we could break this up better - e.g. share OkHttpClient instance across app
    // that would give us two good scopes to showcase - per feature (e.g. RoomFeedDB) and per app
    private fun letterboxdApi(): LetterboxdApi = LetterboxdApiFactory(
            BuildConfig.API_KEY,
            BuildConfig.API_SECRET,
            clock(),
            BuildConfig.DEBUG
    ).remoteLetterboxdApi()
}
