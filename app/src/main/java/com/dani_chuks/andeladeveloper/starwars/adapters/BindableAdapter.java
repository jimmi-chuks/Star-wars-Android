package com.dani_chuks.andeladeveloper.starwars.adapters;

import com.dani_chuks.andeladeveloper.presentation_models.ItemModel;

import java.util.List;

public interface BindableAdapter<T extends ItemModel> {
    void setItems(List<T> itemModel);
}
