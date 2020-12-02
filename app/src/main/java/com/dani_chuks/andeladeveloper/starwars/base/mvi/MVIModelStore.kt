package com.dani_chuks.andeladeveloper.starwars.base.mvi

import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.scan

data class StoreResult(val intent: Intent<MVIState>, val action: MVIAction? = null)

@FlowPreview
@ExperimentalCoroutinesApi
abstract class ModelStore {

    abstract val startingState: MVIState

    abstract val iDispatcherProvider: IDispatcherProvider

    private val intents: ConflatedBroadcastChannel<Intent<*>> = ConflatedBroadcastChannel()

    private val actionChannel: BroadcastChannel<MVIAction> = BroadcastChannel(1)

    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private fun dispatchAction(action: MVIAction) {
        scope.launch(iDispatcherProvider.io) { actionChannel.send(action) }
    }

    fun process(intent: Intent<*>) {
        (intent as? Intent<*>)?.let {
            scope.launch { intents.send(intent) }
        }
    }

    fun actions() = actionChannel.asFlow()

    fun state() = intents.asFlow().intentProcessorFlow()
            .scan(startingState, { oldstate: MVIState, store ->
                store.action?.let { dispatchAction(it) }
                store.intent.reduce(oldstate)
            })

    fun clear() {
        if (scope.isActive) {
            scope.cancel()
        }
    }

//    abstract fun intentProcessorFlow2(): Flow<StoreResult>

    abstract fun Flow<Intent<*>>.intentProcessorFlow(): Flow<StoreResult>

    fun actionStoreResult(block: () -> MVIAction) = storeAction(block)

    fun intentStoreResult(block: MVIState.() -> MVIState) = storeIntent { block() }


    /**
     * Creates a Store Result having an intent where the reduce function is passed as lambda
     * and dispatches no action
     */
    private fun storeIntent(block: MVIState.() -> MVIState): StoreResult =
            StoreResult(intent { block() })

    /**
     * Creates a Store Result having an intent where the reduce function makes no change in state
     * and dispatches an action obtained by applying the block function passed as a parameter
     */
    private fun storeAction(block: () -> MVIAction): StoreResult {
        return StoreResult(noChange(), block())
    }

//    /**
//     * Creates a Store Result having an intent where the reduce function makes no change in state
//     * and dispatches an action obtained by applying the block function on the state to produce an action
//     */
//    private fun storeActionFromState(intentBlock: MVIState.() -> MVIState, actionBlock: (MVIState) -> MVIAction): StoreResult {
//        var state: MVIState? = null
//        val inn = intent<MVIState> {
//            state = this
//            intentBlock(this)
//        }
//        return StoreResult inn, state?.let{ actionBlock(it) })
//    }
//
//        fun actionFromStateStoreResult(intentBlock: MVIState.() -> MVIState, actionBlock: (MVIState) -> MVIAction) =
//            storeActionFromState(intentBlock, actionBlock)
//
//    fun intentActionStoreUpdate(intentBlock: MVIState.() -> MVIState, actionBlock: (() -> A)?) =
//            StoreResultintent<S> { intentBlock() }, actionBlock?.invoke())
}