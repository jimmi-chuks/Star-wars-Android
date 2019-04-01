package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film (
    @PrimaryKey var url: String,
    var title: String?,
    var episodeId: Int?,
    var openingCrawl: String?,
    var director: String?,
    var producer: String?,
    var releaseDate: String?,
    var characters: List<String>?,
    var planets: List<String>?,
    var starships: List<String>?,
    var vehicles: List<String>?,
    var species: List<String>?,
    var created: String?,
    var edited: String?
)
