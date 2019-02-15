package com.dani_chuks.andeladeveloper.presentation_models


import java.util.ArrayList

class VehicleModel : ItemModel {

    var url: String? = null
    var name: String? = null
    var model: String? = null
    var manufacturer: String? = null
    var costInCredits: String? = null
    var length: String? = null
    var maxAtmospheringSpeed: String? = null
    var crew: String? = null
    var passengers: String? = null
    var cargoCapacity: String? = null
    var consumables: String? = null
    var vehicleClass: String? = null
    var pilots: List<String> = ArrayList()
    var films: List<String> = ArrayList()
    var created: String? = null
    var edited: String? = null
    private val itemModelType = ItemModelType.VEHICLE

    override val modelType: Int
        get() = itemModelType.itemValueType


    constructor(url: String) {
        this.url = url
    }

    constructor() {

    }
}
