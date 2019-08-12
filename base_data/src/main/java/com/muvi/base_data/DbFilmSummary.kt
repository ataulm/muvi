package com.muvi.base_data

import androidx.room.*

@Entity(tableName = "film_summary")
data class DbFilmSummary(
        @PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "release_year") val releaseYear: Int?,
        @field:TypeConverters(StringListConverters::class)
        @ColumnInfo(name = "directors") val directors: List<String>,
        // TODO: It's not safe to serialise models like this because
        //  the schema can change — we'd have to nuke the DB on upgrade (sure)
        //  or provide migrations between versions (NO)
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
