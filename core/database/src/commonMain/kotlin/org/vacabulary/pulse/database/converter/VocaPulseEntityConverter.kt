package org.vacabulary.pulse.database.converter

import androidx.room.TypeConverter

private const val SEPARATOR = "¦"

internal class VocaPulseEntityConverter {
    @TypeConverter
    fun listToString(list: List<String>?): String? =
        list?.joinToString(SEPARATOR)

    @TypeConverter
    fun stringToList(s: String?): List<String> =
        if (s.isNullOrEmpty()) emptyList() else s.split(SEPARATOR)
}