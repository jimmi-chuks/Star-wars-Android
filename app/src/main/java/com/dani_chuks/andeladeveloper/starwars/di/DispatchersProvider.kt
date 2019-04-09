package com.dani_chuks.andeladeveloper.starwars.di

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


data class DispatchersProvider @Inject constructor(
        override val main: CoroutineDispatcher,
        override val computation: CoroutineDispatcher,
        override val io: CoroutineDispatcher): IDispatcherProvider

