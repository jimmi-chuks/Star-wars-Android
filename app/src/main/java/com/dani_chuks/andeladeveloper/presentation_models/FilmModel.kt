package com.dani_chuks.andeladeveloper.presentation_models

class FilmModel : ItemModel {

    var url: String? = null
    var title: String? = null
    var episodeId: Int? = null
    var openingCrawl: String? = null
    var director: String? = null
    var producer: String? = null
    var releaseDate: String? = null
    var characters: List<String>? = null
    var planets: List<String>? = null
    var starships: List<String>? = null
    var vehicles: List<String>? = null
    var species: List<String>? = null
    var created: String? = null
    var edited: String? = null
    private val itemModelType = ItemModelType.FILM

    override val modelType: Int
        get() = itemModelType.itemValueType

    constructor(url: String) {
        this.url = url
    }

    constructor() {}
}
