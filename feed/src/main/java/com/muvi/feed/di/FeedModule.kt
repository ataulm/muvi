package com.muvi.feed.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muvi.feed.FeedViewModel
import com.muvi.feed_data.AndroidFilmRepository
import com.muvi.feed_data.FeedCache
import com.muvi.feed_data.FeedRemote
import com.muvi.feed_domain.FilmRepository
import com.muvi.feed_domain.GetFilmsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [FeedModule.ViewModelModule::class])
class FeedModule {

    @Provides
    fun providesFilmRepository(feedRemote: FeedRemote, feedCache: FeedCache): FilmRepository =
        AndroidFilmRepository(feedRemote, feedCache)

    @Provides
    fun providesGetFilmsUseCase(filmRepository: FilmRepository): GetFilmsUseCase =
        GetFilmsUseCase(filmRepository)

    @Module
    interface ViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(FeedViewModel::class)
        fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel

        @Binds
        fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    }
}
