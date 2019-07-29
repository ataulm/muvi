package com.muvi.film_detail.di

import androidx.lifecycle.ViewModelProviders
import com.muvi.BuildConfig
import com.muvi.core.di.CoreComponent
import com.muvi.film_detail.FilmDetailActivity
import com.muvi.film_detail.FilmDetailViewModel
import com.muvi.film_detail.FilmDetailViewModelFactory
import com.muvi.film_detail_data.di.FilmDetailDataModule
import com.muvi.film_detail_remote.DebugMode
import com.muvi.film_detail_remote.FilmDetailRemoteModule
import com.muvi.film_detail_remote.LetterboxdApiKey
import com.muvi.film_detail_remote.LetterboxdApiSecret
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
        modules = [
            FilmDetailModule::class,
            FilmDetailDataModule::class,
            FilmDetailRemoteModule::class
        ],
        dependencies = [CoreComponent::class]
)
internal interface FilmDetailComponent {

    fun inject(activity: FilmDetailActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: FilmDetailActivity): Builder

        fun coreComponent(module: CoreComponent): Builder
        fun build(): FilmDetailComponent
    }
}

@Module
internal object FilmDetailModule {

    @JvmStatic
    @Provides
    fun viewModel(feedFragment: FilmDetailActivity, viewModelFactory: FilmDetailViewModelFactory) =
            ViewModelProviders.of(feedFragment, viewModelFactory).get(FilmDetailViewModel::class.java)

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
