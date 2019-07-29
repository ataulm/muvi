package com.muvi.feed_data

import com.muvi.base_domain.FilmSummary
import com.muvi.feed_domain.FilmRepository
import javax.inject.Inject

internal class CacheAndRemoteFilmRepository @Inject constructor(
        private val feedRemote: FeedRemote,
        private val feedCache: FeedCache
) : FilmRepository {

    override suspend fun getFilms(): List<FilmSummary> {
        try {
            val freshFeed = feedRemote.getFeed()
            feedCache.insertFeed(freshFeed)
        } finally {
            return feedCache.getFeed()
        }
    }
}
