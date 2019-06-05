package com.muvi.remote

import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface LetterboxdApi {

    @GET("films")
    fun films(): Deferred<FilmsResponseModel>
}

@JsonClass(generateAdapter = true)
data class FilmsResponseModel(val next: String, val items: List<FilmSummaryModel>)

@JsonClass(generateAdapter = true)
data class FilmSummaryModel(
        val id: String,
        val name: String,
        val releaseYear: Int,
        val poster: ImageModel?,
        val links: List<LinkModel>)

@JsonClass(generateAdapter = true)
data class ImageModel(val sizes: List<SizeModel>) {

    @JsonClass(generateAdapter = true)
    data class SizeModel(val width: Int, val height: Int, val url: String)
}

@JsonClass(generateAdapter = true)
data class LinkModel(val type: String, val id: String, val url: String)

@JsonClass(generateAdapter = true)
data class GenreModel(val id: String, val name: String)

@JsonClass(generateAdapter = true)
data class FilmModel(val id: String,
                     val name: String,
                     val releaseYear: Int,
                     val tagline: String?,
                     val description: String?,
                     val runTime: Int?,
                     val poster: ImageModel?,
                     val backdrop: ImageModel?,
                     val backdropFocalPoint: Double?,
                     val genres: List<GenreModel>?,
                     val contributions: List<FilmContributionsModel>,
                     val links: List<LinkModel>)

@JsonClass(generateAdapter = true)
data class FilmContributionsModel(val type: String, val contributors: List<ContributorSummaryModel>) {

    @JsonClass(generateAdapter = true)
    open class ContributorSummaryModel(open val id: String, open val name: String)

    @JsonClass(generateAdapter = true)
    data class ActorModel(override val id: String, override val name: String,
                          val characterName: String) : ContributorSummaryModel(id, name)

}
