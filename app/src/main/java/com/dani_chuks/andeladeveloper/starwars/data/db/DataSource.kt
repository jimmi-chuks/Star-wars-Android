package com.dani_chuks.andeladeveloper.starwars.data.db

import io.reactivex.Flowable

interface DataSource<T> {

    val all: Flowable<List<T>>

    val allAlphabetically: Flowable<List<T>>

    fun getItemsLimitedToSize(size: Int): Flowable<List<T>>

    fun getItemByUrl(stringUrl: String): Flowable<T>

    fun insertItem(t: T)

    fun insertItemList(t: List<T>)

}
