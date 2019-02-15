package com.dani_chuks.andeladeveloper.starwars.data.models

import androidx.room.Entity

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class FilmList {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("next")
    @Expose
    var next: Any? = null
    @SerializedName("previous")
    @Expose
    var previous: Any? = null
    @SerializedName("results")
    @Expose
    var film: List<Film>? = null

}
