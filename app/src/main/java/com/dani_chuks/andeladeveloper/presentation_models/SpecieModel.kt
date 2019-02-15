package com.dani_chuks.andeladeveloper.presentation_models

import java.util.ArrayList

class SpecieModel : ItemModel {

    var url: String? = null
    var name: String? = null
    var classification: String? = null
    var designation: String? = null
    var averageHeight: String? = null
    var skinColors: String? = null
    var hairColors: String? = null
    var eyeColors: String? = null
    var averageLifespan: String? = null
    var homeWorld: String? = null
    var language: String? = null
    var people: List<String> = ArrayList()
    var films: List<String> = ArrayList()
    var created: String? = null
    var edited: String? = null
    private val itemModelType = ItemModelType.SPECIE

    override val modelType: Int
        get() = itemModelType.itemValueType

    constructor(url: String) {
        this.url = url
    }

    constructor() {}
}
