package com.muvi

import android.app.Application
import com.muvi.core.di.CoreComponent
import com.muvi.core.di.CoreComponentProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MuViApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent() = coreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = DaggerCoreComponent
            .builder()
            .build()
    }
}
