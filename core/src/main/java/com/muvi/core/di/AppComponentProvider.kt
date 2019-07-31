package com.muvi.core.di

/**
 * Provides the core Dagger DI Component.
 *
 * The core module needs an application context as DI root.
 * Therefore, the application classes of the apps using this module
 * should implement [AppComponentProvider].
 */
interface AppComponentProvider {
    /**
     * Returns the AppComponent / DI root.
     */
    fun provideAppComponent(): AppComponent
}
