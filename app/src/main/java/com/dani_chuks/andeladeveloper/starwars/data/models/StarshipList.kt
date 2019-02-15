package com.dani_chuks.andeladeveloper.starwars.data.models

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StarshipList {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("previous")
    @Expose
    var previous: Any? = null
    @SerializedName("results")
    @Expose
    var starship: List<Starship>? = null

}
