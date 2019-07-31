package com.muvi.core.di

import android.app.Application
import android.content.Context
import com.muvi.remote.LetterboxdApi
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(
        modules = [
            AppModule::class,
            LetterboxdApiModule::class
        ]
)
@Singleton
interface AppComponent {

    fun context(): Context

    fun letterboxdApi(): LetterboxdApi

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
internal abstract class AppModule {

    @Binds
    abstract fun context(application: Application): Context
}
