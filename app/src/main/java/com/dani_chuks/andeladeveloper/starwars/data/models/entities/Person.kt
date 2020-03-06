package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
        @PrimaryKey var url: String,
        var name: String?,
        var height: String?,
        var mass: String?,
        var hairColor: String?,
        var skinColor: String?,
        var eyeColor: String?,
        var birthYear: String?,
        var gender: String?,
        var homeWorld: String?,
        var films: List<String>?,
        var species: List<String>?,
        var vehicles: List<String>?,
        var starships: List<String>?,
        var created: String?,
        var edited: String?
)
