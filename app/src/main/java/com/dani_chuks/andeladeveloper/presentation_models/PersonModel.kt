package com.dani_chuks.andeladeveloper.presentation_models

import java.util.*

class PersonModel : ItemModel {

    var url: String? = null
    var name: String? = null
    var height: String? = null
    var mass: String? = null
    var hairColor: String? = null
    var skinColor: String? = null
    var eyeColor: String? = null
    var birthYear: String? = null
    var gender: String? = null
    var homeWorld: String? = null
    var films: List<String> = ArrayList()
    var species: List<String> = ArrayList()
    var vehicles: List<String> = ArrayList()
    var starships: List<String> = ArrayList()
    var created: String? = null
    var edited: String? = null
    private val itemModelType = ItemModelType.PERSON

    override val modelType: Int
        get() = itemModelType.itemValueType

    constructor(url: String) {
        this.url = url
    }

    constructor() {}
}
