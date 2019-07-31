package com.muvi

import android.app.Application
import com.muvi.core.di.AppComponent
import com.muvi.core.di.AppComponentProvider
import com.muvi.core.di.DaggerAppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MuViApplication : Application(), AppComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun provideAppComponent() = appComponent

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MuViApplication)
        }

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}
