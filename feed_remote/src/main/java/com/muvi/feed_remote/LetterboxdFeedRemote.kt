package com.muvi.feed_remote

import com.muvi.base_domain.FilmSummary
import com.muvi.base_domain.Image
import com.muvi.feed_data.FeedRemote
import com.muvi.remote.LetterboxdApi
import javax.inject.Inject

internal class LetterboxdFeedRemote @Inject constructor(
        private val letterboxdApi: LetterboxdApi
) : FeedRemote {

    override suspend fun getFeed(): List<FilmSummary> {
        return letterboxdApi.films().let { filmsResponseModel ->
            filmsResponseModel.items.map { filmSummaryModel ->
                FilmSummary(
                        id = filmSummaryModel.id,
                        title = filmSummaryModel.name,
                        year = filmSummaryModel.releaseYear,
                        directors = filmSummaryModel.directors.map { it.name },
                        poster = filmSummaryModel.poster?.let { imageModel ->
                            val sizes = imageModel.sizes.map { sizeModel ->
                                Image.Size(sizeModel.width, sizeModel.height, sizeModel.url)
                            }
                            Image(sizes)
                        }
                )
            }
        }
    }
}
