package com.muvi.core.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreModule::class
])
interface CoreComponent {

    fun context(): Context

}