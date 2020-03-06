package com.dani_chuks.andeladeveloper.starwars.base.mvi

//import kotlinx.coroutines.flow.scan
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
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIViewmodel<S, E, A> : ViewModel() {

    private val _viewState: MutableLiveData<S> = MutableLiveData()

    val viewState: LiveData<S>
        get() = _viewState

    private var eventChannel: ConflatedBroadcastChannel<E> = ConflatedBroadcastChannel()

    val actionChannel: BroadcastChannel<A> = BroadcastChannel(1)

    abstract val initialState: S

    abstract val dispatcherProvider: IDispatcherProvider

    open val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    abstract fun reduceState(state: S, event: E): S

    abstract fun getIoContext(): CoroutineContext

    fun onEvent(event: E){
        viewModelScope.launch( dispatcherProvider.io){ eventChannel.offer(event)}
    }

    fun onAction(action: A){
        viewModelScope.launch( dispatcherProvider.io){ actionChannel.offer(action)}
    }

    fun initState() {
        viewModelScope.launch(getIoContext()) {
            val startState = viewState.value ?: initialState
            eventChannel.close()
            eventChannel = ConflatedBroadcastChannel()
            eventChannel.asFlow()
                    .scan(startState, { oldState: S, event: E -> reduceState(oldState, event) })
                    .distinctUntilChanged()
                    .collect { _viewState.postValue(it) }
        }
    }

}