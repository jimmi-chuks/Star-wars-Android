package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Person {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    lateinit var url: String
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null
    @ColumnInfo(name = "height")
    @SerializedName("height")
    @Expose
    var height: String? = null
    @ColumnInfo(name = "mass")
    @SerializedName("mass")
    @Expose
    var mass: String? = null
    @ColumnInfo(name = "hair_color")
    @SerializedName("hair_color")
    @Expose
    var hairColor: String? = null
    @ColumnInfo(name = "skin_color")
    @SerializedName("skin_color")
    @Expose
    var skinColor: String? = null
    @ColumnInfo(name = "eye_color")
    @SerializedName("eye_color")
    @Expose
    var eyeColor: String? = null
    @ColumnInfo(name = "birth_year")
    @SerializedName("birth_year")
    @Expose
    var birthYear: String? = null
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @ColumnInfo(name = "home_world")
    @SerializedName("home_world")
    @Expose
    var homeWorld: String? = null
    @ColumnInfo(name = "films")
    @SerializedName("films")
    @Expose
    var films: List<String>? = null
    @ColumnInfo(name = "species")
    @SerializedName("species")
    @Expose
    var species: List<String>? = null
    @ColumnInfo(name = "vehicles")
    @SerializedName("vehicles")
    @Expose
    var vehicles: List<String>? = null
    @ColumnInfo(name = "starships")
    @SerializedName("starships")
    @Expose
    var starships: List<String>? = null
    @ColumnInfo(name = "created")
    @SerializedName("created")
    @Expose
    var created: String? = null
    @ColumnInfo(name = "edited")
    @SerializedName("edited")
    @Expose
    var edited: String? = null
}
