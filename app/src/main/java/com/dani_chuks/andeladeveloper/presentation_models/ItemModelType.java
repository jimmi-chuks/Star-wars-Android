package com.dani_chuks.andeladeveloper.presentation_models;

public enum ItemModelType {

    FILM(1),
    PERSON(2),
    PLANET(3),
    SPECIE(4),
    STARSHIP(5),
    VEHICLE(6);

    private final int itemValueType;

    private ItemModelType(int itemValueType) {
        this.itemValueType = itemValueType;
    }

    public int getItemValueType() {
        return itemValueType;
    }
}
