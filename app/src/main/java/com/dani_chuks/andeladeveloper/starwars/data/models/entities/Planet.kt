package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Planet(
        @PrimaryKey var url: String,
        var name: String?,
        var rotationPeriod: String?,
        var orbitalPeriod: String?,
        var diameter: String?,
        var climate: String?,
        var gravity: String?,
        var terrain: String?,
        var surfaceWater: String?,
        var population: String?,
        var residents: List<String>?,
        var films: List<String>?,
        var created: String?,
        var edited: String?
)
