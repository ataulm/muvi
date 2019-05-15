package com.tree.nuts

import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface LetterboxdApi {

    @GET("films")
    fun films(): Deferred<FilmsResponse>
}

@JsonClass(generateAdapter = true)
data class FilmsResponse(val next: String, val items: List<FilmSummary>)

@JsonClass(generateAdapter = true)
data class FilmSummary(val name: String,
                       val releaseYear: Int,
                       val poster: Image?,
                       val links: List<Link>)

@JsonClass(generateAdapter = true)
data class Image(val sizes: List<Size>) {

    @JsonClass(generateAdapter = true)
    data class Size(val width: Int, val height: Int, val url: String)
}

@JsonClass(generateAdapter = true)
data class Link(val type: String, val id: String, val url: String)

@JsonClass(generateAdapter = true)
data class Genre(val id: String, val name: String)

@JsonClass(generateAdapter = true)
data class Film(val id: String,
                val name: String,
                val releaseYear: Int,
                val tagline: String?,
                val description: String?,
                val runTime: Int?,
                val poster: Image?,
                val backdrop: Image?,
                val backdropFocalPoint: Double?,
                val genres: List<Genre>?,
                val contributions: List<FilmContributions>,
                val links: List<Link>)

@JsonClass(generateAdapter = true)
data class FilmContributions(val type: String, val contributors: List<ContributorSummary>) {

    @JsonClass(generateAdapter = true)
    open class ContributorSummary(open val id: String, open val name: String)

    @JsonClass(generateAdapter = true)
    data class Actor(override val id: String, override val name: String,
                     val characterName: String) : ContributorSummary(id, name)

}
