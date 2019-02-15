package com.dani_chuks.andeladeveloper.starwars.data.models

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VehicleList {

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
    var vehicle: List<Vehicle>? = null

}
