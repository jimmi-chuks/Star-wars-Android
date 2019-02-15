package com.dani_chuks.andeladeveloper.starwars.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Film {
    @PrimaryKey
    @SerializedName("url")
    @Expose
    lateinit var url: String
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = null
    @ColumnInfo(name = "episode_id")
    @SerializedName("episode_id")
    @Expose
    var episodeId: Int? = null
    @ColumnInfo(name = "opening_crawl")
    @SerializedName("opening_crawl")
    @Expose
    var openingCrawl: String? = null
    @ColumnInfo(name = "director")
    @SerializedName("director")
    @Expose
    var director: String? = null
    @ColumnInfo(name = "producer")
    @SerializedName("producer")
    @Expose
    var producer: String? = null
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @ColumnInfo(name = "characters")
    @SerializedName("characters")
    @Expose
    var characters: List<String>? = null
    @ColumnInfo(name = "planets")
    @SerializedName("planets")
    @Expose
    var planets: List<String>? = null
    @ColumnInfo(name = "starships")
    @SerializedName("starships")
    @Expose
    var starships: List<String>? = null
    @ColumnInfo(name = "vehicles")
    @SerializedName("vehicles")
    @Expose
    var vehicles: List<String>? = null
    @ColumnInfo(name = "species")
    @SerializedName("species")
    @Expose
    var species: List<String>? = null
    @ColumnInfo(name = "created")
    @SerializedName("created")
    @Expose
    var created: String? = null
    @ColumnInfo(name = "edited")
    @SerializedName("edited")
    @Expose
    var edited: String? = null
}
