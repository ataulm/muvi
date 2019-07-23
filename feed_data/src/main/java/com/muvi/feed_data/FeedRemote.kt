package com.muvi.feed_data

import com.muvi.base_domain.FilmSummary

interface FeedRemote {

    suspend fun getFeed(): List<FilmSummary>
}
