package com.dani_chuks.andeladeveloper.starwars.data.models

import com.google.gson.annotations.SerializedName

data class EntityList<T>(
        var count: Int?,
        var next: String?,
        var previous: Any?,
        @SerializedName("results")
        var list: List<T>? = null)


