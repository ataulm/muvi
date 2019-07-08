package com.muvi.remote

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Path

interface LetterboxdApi {

    @GET("films")
    suspend fun films(): FilmsResponseModel

    @GET("film/{id}")
    suspend fun film(@Path("id") id: String): FilmModel

    @GET("contributor/{id}/contributions?type=Actor")
    suspend fun filmsByActor(@Path("id") id: String): FilmContributionsResponseModel
}

@JsonClass(generateAdapter = true)
data class FilmsResponseModel(val next: String, val items: List<FilmSummaryModel>)

@JsonClass(generateAdapter = true)
data class FilmSummaryModel(
        val id: String,
        val name: String,
        val releaseYear: Int?,
        val poster: ImageModel?,
        val directors: List<DirectorModel>
)

@JsonClass(generateAdapter = true)
data class DirectorModel(val name: String)

@JsonClass(generateAdapter = true)
data class ImageModel(val sizes: List<SizeModel>) {

    @JsonClass(generateAdapter = true)
    data class SizeModel(val width: Int, val height: Int, val url: String)
}

@JsonClass(generateAdapter = true)
data class FilmContributionsResponseModel(val items: List<FilmContributionModel>)

@JsonClass(generateAdapter = true)
data class FilmContributionModel(val film: FilmSummaryModel, val characterName: String?)

@JsonClass(generateAdapter = true)
data class FilmModel(
        val id: String,
        val name: String,
        val releaseYear: Int?,
        val tagline: String?,
        val description: String?,
        val runTime: Int?,
        val poster: ImageModel?,
        val backdrop: ImageModel?,
        val backdropFocalPoint: Double?,
        val contributions: List<ContributionModel>
)

@JsonClass(generateAdapter = true)
data class ContributionModel(val type: String, val contributors: List<Contributor>) {

    @JsonClass(generateAdapter = true)
    data class Contributor(val id: String, val name: String, val characterName: String?)
}
