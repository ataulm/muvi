package com.muvi.feed.di

import androidx.lifecycle.ViewModelProviders
import com.muvi.core.di.CoreComponent
import com.muvi.feed.BuildConfig
import com.muvi.feed.FeedFragment
import com.muvi.feed.FeedViewModel
import com.muvi.feed.FeedViewModelFactory
import com.muvi.feed_cache.di.FeedCacheModule
import com.muvi.feed_data.di.FeedDataModule
import com.muvi.feed_remote.di.DebugMode
import com.muvi.feed_remote.di.FeedRemoteModule
import com.muvi.feed_remote.di.LetterboxdApiKey
import com.muvi.feed_remote.di.LetterboxdApiSecret
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
        modules = [
            FeedModule::class,
            FeedDataModule::class,
            FeedCacheModule::class,
            FeedRemoteModule::class
        ],
        dependencies = [CoreComponent::class]
)
internal interface FeedComponent {

    fun inject(fragment: FeedFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragment(fragment: FeedFragment): Builder

        fun coreComponent(module: CoreComponent): Builder
        fun build(): FeedComponent
    }
}

@Module
internal object FeedModule {

    @JvmStatic
    @Provides
    fun viewModel(feedFragment: FeedFragment, viewModelFactory: FeedViewModelFactory) =
            ViewModelProviders.of(feedFragment, viewModelFactory).get(FeedViewModel::class.java)

    @JvmStatic
    @LetterboxdApiKey
    @Provides
    fun letterboxdApiKey() = BuildConfig.API_KEY

    @JvmStatic
    @LetterboxdApiSecret
    @Provides
    fun letterboxdApiSecret() = BuildConfig.API_SECRET

    @JvmStatic
    @DebugMode
    @Provides
    fun debugMode() = BuildConfig.DEBUG
}
