package com.dani_chuks.andeladeveloper.starwars.data.models.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Vehicle {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    lateinit var url: String
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("model")
    @Expose
    var model: String? = null
    @ColumnInfo(name = "manufacturer")
    @SerializedName("manufacturer")
    @Expose
    var manufacturer: String? = null
    @ColumnInfo(name = "cost_in_credits")
    @SerializedName("cost_in_credits")
    @Expose
    var costInCredits: String? = null
    @ColumnInfo(name = "length")
    @SerializedName("length")
    @Expose
    var length: String? = null
    @ColumnInfo(name = "max_atmosphering_speed")
    @SerializedName("max_atmosphering_speed")
    @Expose
    var maxAtmospheringSpeed: String? = null
    @ColumnInfo(name = "crew")
    @SerializedName("crew")
    @Expose
    var crew: String? = null
    @ColumnInfo(name = "passengers")
    @SerializedName("passengers")
    @Expose
    var passengers: String? = null
    @ColumnInfo(name = "cargo_capacity")
    @SerializedName("cargo_capacity")
    @Expose
    var cargoCapacity: String? = null
    @ColumnInfo(name = "consumables")
    @SerializedName("consumables")
    @Expose
    var consumables: String? = null
    @ColumnInfo(name = "vehicle_class")
    @SerializedName("vehicle_class")
    @Expose
    var vehicleClass: String? = null
    @ColumnInfo(name = "pilots")
    @SerializedName("pilots")
    @Expose
    var pilots: List<String>? = null
    @ColumnInfo(name = "films")
    @SerializedName("films")
    @Expose
    var films: List<String>? = null
    @ColumnInfo(name = "created")
    @SerializedName("created")
    @Expose
    var created: String? = null
    @ColumnInfo(name = "edited")
    @SerializedName("edited")
    @Expose
    var edited: String? = null
}
