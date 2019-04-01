package com.dani_chuks.andeladeveloper.presentation_models

data class SpecieModel(
        var url: String = "",
        var name: String = "",
        var classification: String = "",
        var designation: String = "",
        var averageHeight: String = "",
        var skinColors: String = "",
        var hairColors: String = "",
        var eyeColors: String = "",
        var averageLifespan: String = "",
        var homeWorld: String = "",
        var language: String = "",
        var people: List<String> = emptyList(),
        var films: List<String> = emptyList(),
        var created: String = "",
        var edited: String = "") : ItemModel {
    override val modelType: Int = ItemModelType.SPECIE.itemValueType
}
