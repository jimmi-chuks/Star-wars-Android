package com.dani_chuks.andeladeveloper.presentation_models

import java.util.*

enum class ItemModelType {
    FILM, PERSON, PLANET, SPECIE, STARSHIP, VEHICLE
}

sealed class MainModels {
    abstract val modelType: ItemModelType
    abstract val url: String

    data class FilmModel(
            override val url: String = "",
            val title: String = "",
            val episodeId: Int?,
            val openingCrawl: String = "",
            val director: String = "",
            val producer: String = "",
            val releaseDate: String = "",
            val characters: List<String> = emptyList(),
            val planets: List<String> = emptyList(),
            val starships: List<String> = emptyList(),
            val vehicles: List<String> = emptyList(),
            val species: List<String> = emptyList(),
            val created: String = "",
            val edited: String = "",
            override val modelType:ItemModelType = ItemModelType.FILM
    ) : MainModels()

    data class PersonModel(
            override val url: String = "",
            val name: String = "",
            val height: String = "",
            val mass: String = "",
            val hairColor: String = "",
            val skinColor: String = "",
            val eyeColor: String = "",
            val birthYear: String = "",
            val gender: String = "",
            val homeWorld: String = "",
            val films: List<String> = ArrayList(),
            val species: List<String> = ArrayList(),
            val vehicles: List<String> = ArrayList(),
            val starships: List<String> = ArrayList(),
            val created: String = "",
            val edited: String= "",
            override val modelType: ItemModelType = ItemModelType.PERSON
    ) : MainModels()

    data class PlanetModel(
            override val url: String = "",
            val name: String = "",
            val rotationPeriod: String = "",
            val orbitalPeriod: String = "",
            val diameter: String = "",
            val climate: String = "",
            val gravity: String = "",
            val terrain: String = "",
            val surfaceWater: String = "",
            val population: String = "",
            val residents: List<String> = emptyList(),
            val films: List<String> = emptyList(),
            val created: String = "",
            val edited: String = "",
            override val modelType: ItemModelType = ItemModelType.PLANET
    ) : MainModels()

    data class SpecieModel(
            override val url: String = "",
            val name: String = "",
            val classification: String = "",
            val designation: String = "",
            val averageHeight: String = "",
            val skinColors: String = "",
            val hairColors: String = "",
            val eyeColors: String = "",
            val averageLifespan: String = "",
            val homeWorld: String = "",
            val language: String = "",
            val people: List<String> = emptyList(),
            val films: List<String> = emptyList(),
            val created: String = "",
            val edited: String = "",
            override val modelType: ItemModelType = ItemModelType.SPECIE
    ) : MainModels()

    data class StarshipModel(
            override val url: String = "",
            val name: String = "",
            val model: String = "",
            val manufacturer: String = "",
            val costInCredits: String = "",
            val length: String = "",
            val maxAtmospheringSpeed: String = "",
            val crew: String = "",
            val passengers: String = "",
            val cargoCapacity: String = "",
            val consumables: String = "",
            val hyperdriveRating: String = "",
            val mglt: String = "",
            val starshipClass: String = "",
            val pilots: List<String> = emptyList(),
            val films: List<String> = emptyList(),
            val created: String = "",
            override val modelType: ItemModelType = ItemModelType.STARSHIP
    ) : MainModels()

    data class VehicleModel(
            override val url: String = "",
            val name: String = "",
            val model: String = "",
            val manufacturer: String = "",
            val costInCredits: String = "",
            val length: String = "",
            val maxAtmospheringSpeed: String = "",
            val crew: String = "",
            val passengers: String = "",
            val cargoCapacity: String = "",
            val consumables: String = "",
            val vehicleClass: String = "",
            val pilots: List<String> = emptyList(),
            val films: List<String> = emptyList(),
            val created: String = "",
            override val modelType: ItemModelType = ItemModelType.VEHICLE
    ) : MainModels()
}