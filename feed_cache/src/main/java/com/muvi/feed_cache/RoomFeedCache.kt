package com.muvi.feed_cache

import com.muvi.base_data.DbFilmSummary
import com.muvi.base_data.DbImage
import com.muvi.base_data.DbImageSize
import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.feed_data.FeedCache

internal class RoomFeedCache(private val feedDao: FeedDao) : FeedCache {

    override suspend fun getFeed(): List<FilmSummary> {
        return feedDao.getFilms().map { dbFilmSummary ->
            FilmSummary(
                    dbFilmSummary.id,
                    dbFilmSummary.name,
                    dbFilmSummary.releaseYear,
                    dbFilmSummary.directors,
                    Image(sizes = dbFilmSummary.poster.sizes.map { dbImageSize ->
                        Image.Size(dbImageSize.width, dbImageSize.height, dbImageSize.url)
                    })
            )
        }
    }

    override suspend fun insertFeed(feed: List<FilmSummary>) {
        return feedDao.insertAll(feed.map { filmSummary ->
            DbFilmSummary(
                    filmSummary.id,
                    filmSummary.title,
                    filmSummary.year,
                    filmSummary.directors,
                    DbImage(sizes = filmSummary.poster?.sizes?.map { DbImageSize(it.width, it.height, it.url) }
                            ?: emptyList())
            )
        })
    }
}
