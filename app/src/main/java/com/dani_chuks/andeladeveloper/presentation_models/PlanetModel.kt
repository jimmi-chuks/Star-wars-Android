package com.dani_chuks.andeladeveloper.presentation_models


data class PlanetModel(
        var url: String = "",
        var name: String = "",
        var rotationPeriod: String = "",
        var orbitalPeriod: String = "",
        var diameter: String = "",
        var climate: String = "",
        var gravity: String = "",
        var terrain: String = "",
        var surfaceWater: String = "",
        var population: String = "",
        var residents: List<String> = emptyList(),
        var films: List<String> = emptyList(),
        var created: String = "",
        var edited: String = "") : ItemModel {
    override val modelType: Int = ItemModelType.PLANET.itemValueType
}
