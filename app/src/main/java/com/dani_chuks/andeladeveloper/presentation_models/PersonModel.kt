package com.dani_chuks.andeladeveloper.presentation_models

import java.util.*

data class PersonModel(
        val url: String = "",
        var name: String = "",
        var height: String = "",
        var mass: String = "",
        var hairColor: String = "",
        var skinColor: String = "",
        var eyeColor: String = "",
        var birthYear: String = "",
        var gender: String = "",
        var homeWorld: String = "",
        var films: List<String> = ArrayList(),
        var species: List<String> = ArrayList(),
        var vehicles: List<String> = ArrayList(),
        var starships: List<String> = ArrayList(),
        var created: String = "",
        var edited: String= "") : ItemModel {

    override val modelType: Int
        get() = ItemModelType.PERSON.itemValueType

}
