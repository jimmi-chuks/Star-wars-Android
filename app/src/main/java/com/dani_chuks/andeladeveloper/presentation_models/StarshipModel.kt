package com.dani_chuks.andeladeveloper.presentation_models

data class StarshipModel(
        var url: String = "",
        var name: String = "",
        var model: String = "",
        var manufacturer: String = "",
        var costInCredits: String = "",
        var length: String = "",
        var maxAtmospheringSpeed: String = "",
        var crew: String = "",
        var passengers: String = "",
        var cargoCapacity: String = "",
        var consumables: String = "",
        var hyperdriveRating: String = "",
        var mglt: String = "",
        var starshipClass: String = "",
        var pilots: List<String> = emptyList(),
        var films: List<String> = emptyList(),
        var created: String = "",
        var edited: String = "") : ItemModel {
    override val modelType: Int = ItemModelType.STARSHIP.itemValueType
}
