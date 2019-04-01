package com.dani_chuks.andeladeveloper.presentation_models

data class FilmModel(
        var url: String = "",
        var title: String = "",
        var episodeId: Int?,
        var openingCrawl: String = "",
        var director: String = "",
        var producer: String = "",
        var releaseDate: String = "",
        var characters: List<String> = emptyList(),
        var planets: List<String> = emptyList(),
        var starships: List<String> = emptyList(),
        var vehicles: List<String> = emptyList(),
        var species: List<String> = emptyList(),
        var created: String = "",
        var edited: String = "") : ItemModel {
    override val modelType: Int get() = ItemModelType.FILM.itemValueType
}

