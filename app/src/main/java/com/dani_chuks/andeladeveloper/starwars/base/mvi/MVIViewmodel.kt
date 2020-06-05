package com.dani_chuks.andeladeveloper.starwars.base.mvi


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIViewmodel<S, E, A> : ViewModel() {

    private val _viewState: MutableLiveData<S> = MutableLiveData()

    val viewState: LiveData<S>
        get() = _viewState

    abstract val modelStore: ModelStore<S, A>

    abstract val dispatcherProvider: IDispatcherProvider

    abstract fun toIntent(event: E): Intent<S>

    val actionFlow = modelStore.actions()

    open val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    fun onEvent(event: E){
        modelStore.process(toIntent(event))
    }

    fun initState() {
        modelStore.state()
                .onEach { _viewState.postValue(it) }
                .launchIn(viewModelScope)
    }

}