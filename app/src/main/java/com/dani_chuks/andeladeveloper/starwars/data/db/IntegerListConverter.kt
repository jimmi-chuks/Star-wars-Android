package com.dani_chuks.andeladeveloper.starwars.data.db

import androidx.room.TypeConverter

import java.util.ArrayList

class IntegerListConverter {
    @TypeConverter
    fun gettingListFromString(urlIds: String): List<Int> {
        val list = ArrayList<Int>()

        val array = urlIds.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        for (s in array) {
            list.add(Integer.parseInt(s))
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String {
        var urlId = ""
        for (i in list) {
            urlId += ",$i"
        }
        return urlId
    }
}
