package com.muvi.core.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun context(): Context

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
}
