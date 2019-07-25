package com.muvi.feed_remote

import com.muvi.feed_data.BuildConfig
import com.muvi.feed_data.FeedRemote
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory
import dagger.Module
import dagger.Provides
import org.koin.dsl.module

@Module
class FeedRemoteModule {

    @Provides
    internal fun providesFeedRemote(): FeedRemote = LetterboxdFeedRemote(providesLetterboxdApi())

    private fun providesClock(): Clock = object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    }

    private fun providesLetterboxdApi(): LetterboxdApi = LetterboxdApiFactory(
        BuildConfig.API_KEY,
        BuildConfig.API_SECRET,
        providesClock(),
        BuildConfig.DEBUG
    ).remoteLetterboxdApi()
}