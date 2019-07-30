package com.muvi.feed_remote.di

import com.muvi.feed_data.FeedRemote
import com.muvi.feed_remote.LetterboxdFeedRemote
import com.muvi.remote.LetterboxdApi
import dagger.Module
import dagger.Provides

@Module
object FeedRemoteModule {

    @JvmStatic
    @Provides
    fun feedRemote(
            letterboxdApi: LetterboxdApi
    ): FeedRemote = LetterboxdFeedRemote(letterboxdApi)
}
