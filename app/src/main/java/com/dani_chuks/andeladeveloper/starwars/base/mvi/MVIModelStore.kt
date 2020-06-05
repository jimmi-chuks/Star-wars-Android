package com.dani_chuks.andeladeveloper.starwars.base.mvi

import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class StoreResult<S, A>(val intent: Intent<S>, val action: A? = null)

@FlowPreview
@ExperimentalCoroutinesApi
abstract class ModelStore<S, A>() {

    abstract val startingState: S

    abstract val scope: CoroutineScope

    abstract val iDispatcherProvider: IDispatcherProvider

    private val intents: ConflatedBroadcastChannel<Intent<S>> = ConflatedBroadcastChannel()

    private val actionChannel: BroadcastChannel<A> = BroadcastChannel(1)

    private fun dispatchAction(action: A) {
        scope.launch(iDispatcherProvider.io) { actionChannel.send(action) }
    }

    fun process(intent: Intent<S>) = scope.launch { intents.send(intent) }

    abstract fun Flow<Intent<S>>.toPartialChangeFlow(): Flow<StoreResult<S, A>>

    fun actions() = actionChannel.asFlow()

    fun state() = intents.asFlow().toPartialChangeFlow()
            .onEach { it.action?.let { dispatchAction(it) } }
            .map { it.intent }
            .scan(startingState, { oldstate, intent -> intent.reduce(oldstate) })
}