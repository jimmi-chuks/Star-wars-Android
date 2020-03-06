package com.dani_chuks.andeladeveloper.starwars.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject


class SharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences, private val context: Context) {

    fun setDataTypeFetchedOnce(resourceName: String) {
        sharedPreferences.edit { putBoolean(resourceName, true) }
    }

    fun isDataTypeFetchedOnce(resourceName: String): Boolean =
            sharedPreferences.getBoolean(resourceName, false)


    fun setResourceNextPage(resourceName: String, nextPage: Int) {
        sharedPreferences.edit { putInt(resourceName, nextPage) }
    }

    fun getResourceNextPage(resourceName: String): Int =
            sharedPreferences.getInt(resourceName, 1)
}
