package com.dani_chuks.andeladeveloper.starwars.data.models

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class People {

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
    var person: List<Person>? = null

}
