package com.dani_chuks.andeladeveloper.starwars.base.mvi


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIViewmodel : ViewModel() {

    abstract val modelStore: ModelStore
    abstract fun toIntent(event: MVIEvent): Intent<*>?

    fun actionFlow() = modelStore.actions()

    fun stateFlow() = modelStore.state()

    fun onEvent(event: MVIEvent){
        toIntent(event)?.let{
            modelStore.process(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        modelStore.clear()
    }

}