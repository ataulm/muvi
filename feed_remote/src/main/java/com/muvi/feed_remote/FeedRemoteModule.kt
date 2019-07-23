package com.muvi.feed_remote

import com.muvi.feed_data.BuildConfig
import com.muvi.feed_data.FeedRemote
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApiFactory
import org.koin.dsl.module

val feedRemoteModule = module(override = true) {
    factory<Clock> {
        object : Clock {
            override fun currentTimeMillis(): Long = System.currentTimeMillis()
        }
    }

    factory {
        LetterboxdApiFactory(
                BuildConfig.API_KEY,
                BuildConfig.API_SECRET,
                clock = get(),
                enableHttpLogging = BuildConfig.DEBUG
        ).remoteLetterboxdApi()
    }

    factory<FeedRemote> {
        LetterboxdFeedRemote(letterboxdApi = get())
    }
}
