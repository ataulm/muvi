package com.muvi.core.di

import android.app.Application
import android.content.Context
import com.muvi.core.BuildConfig
import com.muvi.remote.Clock
import com.muvi.remote.LetterboxdApi
import com.muvi.remote.LetterboxdApiFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun context(): Context
    fun letterboxdApi(): LetterboxdApi

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }
}

@Module
internal object CoreModule {

    @JvmStatic
    @Provides
    fun context(application: Application): Context = application

    private fun clock(): Clock = object : Clock {
        override fun currentTimeMillis(): Long = System.currentTimeMillis()
    }

    // TODO: we could break this up better - e.g. share OkHttpClient instance across app
    // that would give us two good scopes to showcase - per feature (e.g. RoomFeedDB) and per app
    @JvmStatic
    @Provides
    fun letterboxdApi(@LetterboxdApiKey apiKey: String, @LetterboxdApiSecret apiSecret: String,
                      @DebugMode debugMode: Boolean): LetterboxdApi = LetterboxdApiFactory(
        apiKey,
        apiSecret,
        clock(),
        debugMode
    ).remoteLetterboxdApi()

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

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LetterboxdApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LetterboxdApiSecret

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugMode