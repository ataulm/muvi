package com.muvi.base_data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "film_summary")
data class DbFilmSummary(
        @PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "release_year") val releaseYear: Int,
        @field:TypeConverters(CustomTypeConverters::class)
        @ColumnInfo(name = "directors") val directors: List<String>,
        @Embedded val poster: DbImageSize
)

data class DbImageSize(
        val width: Int,
        val height: Int,
        val url: String
)
