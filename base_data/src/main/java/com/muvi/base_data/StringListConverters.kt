package com.muvi.base_data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType

internal class StringListConverters {

    private val type = newParameterizedType(List::class.java, String::class.java)
    private val adapter = Moshi.Builder().build().adapter<List<String>>(type)

    @TypeConverter
    fun toString(list: List<String>) = adapter.toJson(list)

    @TypeConverter
    fun fromString(json: String) = adapter.fromJson(json)
}
