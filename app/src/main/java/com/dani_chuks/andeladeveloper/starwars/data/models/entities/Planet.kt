package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Planet {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    lateinit var url: String
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null
    @ColumnInfo(name = "rotation_period")
    @SerializedName("rotation_period")
    @Expose
    var rotationPeriod: String? = null
    @ColumnInfo(name = "orbital_period")
    @SerializedName("orbital_period")
    @Expose
    var orbitalPeriod: String? = null
    @ColumnInfo(name = "diameter")
    @SerializedName("diameter")
    @Expose
    var diameter: String? = null
    @ColumnInfo(name = "climate")
    @SerializedName("climate")
    @Expose
    var climate: String? = null
    @ColumnInfo(name = "gravity")
    @SerializedName("gravity")
    @Expose
    var gravity: String? = null
    @ColumnInfo(name = "terrain")
    @SerializedName("terrain")
    @Expose
    var terrain: String? = null
    @ColumnInfo(name = "surface_water")
    @SerializedName("surface_water")
    @Expose
    var surfaceWater: String? = null
    @ColumnInfo(name = "population")
    @SerializedName("population")
    @Expose
    var population: String? = null
    @ColumnInfo(name = "residents")
    @SerializedName("residents")
    @Expose
    var residents: List<String>? = null
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
