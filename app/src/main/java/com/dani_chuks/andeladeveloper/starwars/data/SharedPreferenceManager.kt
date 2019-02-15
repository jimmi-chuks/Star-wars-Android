package com.dani_chuks.andeladeveloper.starwars.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class SharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences, private val context: Context) {


    fun setDataTypeFetchedOnce(resourceName: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(resourceName, true)
        editor.apply()
    }

    fun isDataTypeFetchedOnce(resourceName: String): Boolean {
        return sharedPreferences.getBoolean(resourceName, false)
    }

    fun setResourceNextPage(resourceName: String, nextPage: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(resourceName, nextPage)
        editor.apply()
    }

    fun getResourceNextPage(resourceName: String): Int {
        return sharedPreferences.getInt(resourceName, 1)
    }
}
