package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.starwars.base.mvi.*
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.home.HomeEvent.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(store: HomeModelStore)
    : MVIViewmodel() {

    val homeState: MutableLiveData<HomeState> = MutableLiveData()

    override val modelStore: ModelStore = store

    override fun toIntent(event: MVIEvent): Intent<MVIState>? {
        return (event as? HomeEvent)?.let{
            when (event) {
                InitEvents -> HomeIntents.Init
                is ShowAllEvent -> HomeIntents.ShowAllIntent(event.itemModelType)
                is ShowItemEvent -> HomeIntents.ShowItemIntent(event.itemURL, event.itemModelType)
            }
        } as Intent<MVIState>
    }

    fun collectState(){
        stateFlow().filterIsInstance<HomeState>()
                .onEach { homeState.postValue(it)}
                .launchIn(viewModelScope)
    }
}
