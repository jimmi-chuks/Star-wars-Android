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
abstract class MVIViewmodel<S, E, A> : ViewModel() {

    private val _viewState: MutableLiveData<S> = MutableLiveData()

    val viewState: LiveData<S>
        get() = _viewState

    abstract val modelStore: ModelStore<S, A>


    abstract fun toIntent(event: E): Intent<S>

    fun actionFlow() = modelStore.actions()

    fun onEvent(event: E){
        modelStore.process(toIntent(event))
    }

    fun initState() {
        modelStore.state()
                .onEach { _viewState.postValue(it) }
                .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        modelStore.clear()
    }

}