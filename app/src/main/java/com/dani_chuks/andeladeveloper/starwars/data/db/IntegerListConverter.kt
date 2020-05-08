package com.dani_chuks.andeladeveloper.starwars.data.db

import androidx.room.TypeConverter
import java.lang.StringBuilder

import java.util.ArrayList

class IntegerListConverter {
    @TypeConverter
    fun gettingListFromString(urlIds: String): List<Int> =
            urlIds.split(",".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                    .map { Integer.parseInt(it) }
                    .toList()

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String {
        val stringBuilder = StringBuilder()
        list.forEach { 
            stringBuilder.append(",$it")
        }
        return stringBuilder.toString()
    }
}
