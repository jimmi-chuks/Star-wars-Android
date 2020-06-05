package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.mvi.Intent
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIViewmodel
import com.dani_chuks.andeladeveloper.starwars.base.mvi.ModelStore
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.home.HomeEvent.*
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewAction.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
        interactor: HomeViewModelInteractor,
        override val dispatcherProvider: IDispatcherProvider)
    : MVIViewmodel<HomeState, HomeEvent, HomeViewAction>() {


    val  printError = { exception: Exception, message: String ->
        println("Error fetching $message: ${exception.message}")
    }

    override val modelStore: ModelStore<HomeState, HomeViewAction> =
            HomeModelStore(interactor, viewModelScope, dispatcherProvider)

    override fun toIntent(event: HomeEvent): Intent<HomeState> =
            when(event){
                InitEvents -> HomeIntents.Init
                is FetchAllEvent -> HomeIntents.ObserveItems(event.type)
                is ShowAllEvent -> HomeIntents.ShowAllIntent(event.itemModelType)
                is ShowItemEvent -> HomeIntents.ShowItemIntent(event.itemURL, event.itemModelType)
            }
}
