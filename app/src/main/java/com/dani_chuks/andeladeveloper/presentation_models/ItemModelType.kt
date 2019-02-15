package com.dani_chuks.andeladeveloper.presentation_models

enum class ItemModelType private constructor(val itemValueType: Int) {

    FILM(1),
    PERSON(2),
    PLANET(3),
    SPECIE(4),
    STARSHIP(5),
    VEHICLE(6),
    CHARACTER(7)
}
