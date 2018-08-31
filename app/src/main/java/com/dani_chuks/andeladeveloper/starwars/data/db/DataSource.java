package com.dani_chuks.andeladeveloper.starwars.data.db;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSource<T> {

    Flowable<List<T>> getAll();

    Flowable<List<T>> getItemsLimitedToSize(int size);

    Flowable<List<T>> getAllAlphabetically();

    Flowable<T> getItemByUrl(String stringUrl);

    void insertItem(T t);

    void insertItemList(List<T> t);

}
