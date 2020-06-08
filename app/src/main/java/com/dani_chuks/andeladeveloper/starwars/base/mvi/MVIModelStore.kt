package com.dani_chuks.andeladeveloper.starwars.base.mvi

import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

data class StoreResult<S, A>(val intent: Intent<S>, val action: A? = null)

@FlowPreview
@ExperimentalCoroutinesApi
abstract class ModelStore<S, A>() {

    abstract val startingState: S

    abstract val iDispatcherProvider: IDispatcherProvider

    private val intents: ConflatedBroadcastChannel<Intent<S>> = ConflatedBroadcastChannel()

    private val actionChannel: BroadcastChannel<A> = BroadcastChannel(1)

    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private fun dispatchAction(action: A) {
        scope.launch(iDispatcherProvider.io) { actionChannel.send(action) }
    }

    fun clear(){
        if(scope.isActive) {
            scope.cancel()
        }
    }

    fun process(intent: Intent<S>) = scope.launch { intents.send(intent) }

    abstract fun Flow<Intent<S>>.intentProcessorFlow(): Flow<StoreResult<S, A>>

    fun actions() = actionChannel.asFlow()

    fun state() = intents.asFlow().intentProcessorFlow()
            .scan(startingState, { oldstate, store ->
                store.action?.let { dispatchAction(it) }
                store.intent.reduce(oldstate)
            })

    fun actionStoreResult(block: () -> A) = storeAction<S,A>(block)

    fun intentStoreResult(block: S.() -> S) = storeIntent<S,A> {  block() }

    fun intentActionStoreUpdate(intentBlock: S.() -> S, actionBlock: (() -> A)?) =
            StoreResult<S, A>(intent<S> { intentBlock() }, actionBlock?.invoke())

    fun actionFromStateStoreResult(intentBlock: S.() -> S, actionBlock: (S) -> A) =
            storeActionFromState<S,A>(intentBlock, actionBlock)


    /**
     * Creates a Store Result having an intent where the reduce function is passed as lambda
     * and dispatches no action
     */
    private fun <S, A> storeIntent(block: S.() -> S): StoreResult<S, A> =
            StoreResult(intent { block() })

    /**
     * Creates a Store Result having an intent where the reduce function makes no change in state
     * and dispatches an action obtained by applying the block function on the state to produce an action
     */
    private fun <S, A> storeActionFromState(intentBlock: S.() -> S, actionBlock: (S) -> A): StoreResult<S, A> {
        var state: S? = null
        val inn = intent<S> {
            state = this
            intentBlock(this)
        }
        return StoreResult(inn, state?.let{ actionBlock(it) })
    }

    /**
     * Creates a Store Result having an intent where the reduce function makes no change in state
     * and dispatches an action obtained by applying the block function passed as a parameter
     */
    private fun <S, A> storeAction(block: () -> A): StoreResult<S, A> {
        return StoreResult(noChange(), block())
    }
}