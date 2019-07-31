package com.muvi.feed.di

import com.muvi.core.di.appComponent
import com.muvi.feed.FeedFragment

internal fun inject(fragment: FeedFragment) {
    DaggerFeedComponent.builder()
            .coreComponent(fragment.appComponent())
            .fragment(fragment)
            .build()
            .inject(fragment)
}
