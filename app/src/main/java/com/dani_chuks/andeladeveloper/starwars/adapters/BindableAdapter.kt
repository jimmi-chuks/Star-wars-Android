package com.dani_chuks.andeladeveloper.starwars.adapters

import com.dani_chuks.andeladeveloper.presentation_models.ItemModel

interface BindableAdapter<T : ItemModel> {
    fun setItems(itemModel: List<T>?)
}
