package com.muvi.base_data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType

internal class DbImageSizeListConverters {

    private val type = newParameterizedType(List::class.java, DbImageSize::class.java)
    private val adapter = Moshi.Builder().build().adapter<List<DbImageSize>>(type)

    @TypeConverter
    fun toString(list: List<DbImageSize>) = adapter.toJson(list)

    @TypeConverter
    fun fromString(json: String) = adapter.fromJson(json)
}
