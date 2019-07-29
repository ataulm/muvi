package com.muvi.feed.di

import com.muvi.core.di.coreComponent
import com.muvi.feed.FeedFragment

internal fun inject(fragment: FeedFragment) {
    DaggerFeedComponent.builder()
            .coreComponent(fragment.coreComponent())
            .fragment(fragment)
            .build()
            .inject(fragment)
}
