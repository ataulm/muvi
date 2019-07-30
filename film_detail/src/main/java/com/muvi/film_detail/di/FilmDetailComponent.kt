package com.muvi.film_detail.di

import androidx.lifecycle.ViewModelProviders
import com.muvi.core.di.CoreComponent
import com.muvi.film_detail.FilmDetailActivity
import com.muvi.film_detail.FilmDetailViewModel
import com.muvi.film_detail.FilmDetailViewModelFactory
import com.muvi.film_detail_data.di.FilmDetailDataModule
import com.muvi.film_detail_remote.FilmDetailRemoteModule
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
    fun viewModel(filmDetailActivity: FilmDetailActivity, viewModelFactory: FilmDetailViewModelFactory) =
            ViewModelProviders.of(filmDetailActivity, viewModelFactory).get(FilmDetailViewModel::class.java)
}
