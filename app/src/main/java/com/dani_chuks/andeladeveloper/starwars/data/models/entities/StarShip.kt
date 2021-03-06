package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarShip(
        @PrimaryKey var url: String,
        var name: String?,
        var model: String?,
        var manufacturer: String?,
        var costInCredits: String?,
        var length: String?,
        var maxAtmospheringSpeed: String?,
        var crew: String?,
        var passengers: String?,
        var cargoCapacity: String?,
        var consumables: String?,
        var hyperdriveRating: String?,
        var mglt: String?,
        var starshipClass: String?,
        var pilots: List<String>?,
        var films: List<String>?,
        var created: String?,
        var edited: String?
)
