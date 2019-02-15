package com.dani_chuks.andeladeveloper.presentation_models

import java.util.ArrayList

class PlanetModel : ItemModel {

    var url: String? = null
    var name: String? = null
    var rotationPeriod: String? = null
    var orbitalPeriod: String? = null
    var diameter: String? = null
    var climate: String? = null
    var gravity: String? = null
    var terrain: String? = null
    var surfaceWater: String? = null
    var population: String? = null
    var residents: List<String> = ArrayList()
    var films: List<String> = ArrayList()
    var created: String? = null
    var edited: String? = null
    private val itemModelType = ItemModelType.PLANET


    override val modelType: Int
        get() = itemModelType.itemValueType

    constructor(url: String) {
        this.url = url
    }

    constructor() {}
}
