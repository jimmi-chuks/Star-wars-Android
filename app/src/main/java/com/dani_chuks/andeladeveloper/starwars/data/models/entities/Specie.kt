package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specie (
    @PrimaryKey var url: String= "",
    var name: String?,
    var classification: String?,
    var designation: String?,
    var averageHeight: String?,
    var skinColors: String?,
    var hairColors: String?,
    var eyeColors: String?,
    var averageLifespan: String?,
    var homeWorld: String?,
    var language: String?,
    var people: List<String>?,
    var films: List<String>?,
    var created: String?,
    var edited: String?
)
