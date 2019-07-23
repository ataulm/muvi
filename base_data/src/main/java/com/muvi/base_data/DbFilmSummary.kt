package com.muvi.base_data

import androidx.room.*

@Entity(tableName = "film_summary")
data class DbFilmSummary(
        @PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "release_year") val releaseYear: Int?,
        @field:TypeConverters(StringListConverters::class)
        @ColumnInfo(name = "directors") val directors: List<String>,
        @Embedded val poster: DbImage
)

data class DbImage(
        @field:TypeConverters(DbImageSizeListConverters::class)
        val sizes: List<DbImageSize> = emptyList()
)

data class DbImageSize(
        @ColumnInfo(name = "width") val width: Int,
        @ColumnInfo(name = "height") val height: Int,
        @ColumnInfo(name = "url") val url: String
)
