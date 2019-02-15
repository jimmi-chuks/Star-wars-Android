package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Specie {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    lateinit var url: String
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = null
    @ColumnInfo(name = "classification")
    @SerializedName("classification")
    @Expose
    var classification: String? = null
    @ColumnInfo(name = "designation")
    @SerializedName("designation")
    @Expose
    var designation: String? = null
    @ColumnInfo(name = "average_height")
    @SerializedName("average_height")
    @Expose
    var averageHeight: String? = null
    @ColumnInfo(name = "skin_colors")
    @SerializedName("skin_colors")
    @Expose
    var skinColors: String? = null
    @ColumnInfo(name = "hair_colors")
    @SerializedName("hair_colors")
    @Expose
    var hairColors: String? = null
    @ColumnInfo(name = "eye_colors")
    @SerializedName("eye_colors")
    @Expose
    var eyeColors: String? = null
    @ColumnInfo(name = "average_lifespan")
    @SerializedName("average_lifespan")
    @Expose
    var averageLifespan: String? = null
    @ColumnInfo(name = "home_world")
    @SerializedName("home_world")
    @Expose
    var homeWorld: String? = null
    @ColumnInfo(name = "language")
    @SerializedName("language")
    @Expose
    var language: String? = null
    @ColumnInfo(name = "people")
    @SerializedName("people")
    @Expose
    var people: List<String>? = null
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
