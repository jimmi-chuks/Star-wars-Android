package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.starwars.base.mvi.Intent
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIViewmodel
import com.dani_chuks.andeladeveloper.starwars.base.mvi.ModelStore
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.home.HomeEvent.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(store: HomeModelStore)
    : MVIViewmodel<HomeState, HomeEvent, HomeViewAction>() {

    override val modelStore: ModelStore<HomeState, HomeViewAction> = store

    override fun toIntent(event: HomeEvent): Intent<HomeState> =
            when(event){
                InitEvents -> HomeIntents.Init
                is ShowAllEvent -> HomeIntents.ShowAllIntent(event.itemModelType)
                is ShowItemEvent -> HomeIntents.ShowItemIntent(event.itemURL, event.itemModelType)
            }
}
