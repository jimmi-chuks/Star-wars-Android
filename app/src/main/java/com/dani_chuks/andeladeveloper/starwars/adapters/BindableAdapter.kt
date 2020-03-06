package com.dani_chuks.andeladeveloper.starwars.adapters

import com.dani_chuks.andeladeveloper.presentation_models.MainModels

interface BindableAdapter<T : MainModels> {
    fun setItems(itemModel: List<T>)
}
