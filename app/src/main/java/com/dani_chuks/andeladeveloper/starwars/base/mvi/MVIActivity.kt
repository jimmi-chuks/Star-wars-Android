package com.dani_chuks.andeladeveloper.starwars.base.mvi

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIActivity<S, E, A, V : MVIViewmodel<S, E, A>> : AppCompatActivity() {

    abstract val viewModel: V

    abstract fun viewEvents(): Flow<E>

    abstract fun handleAction(action: A)

    abstract fun initViewModel()

    private var scope: CoroutineScope? =  null

    override fun onStop() {
        super.onStop()
        scope?.cancel()
     }

    override fun onStart() {
        super.onStart()
        scope = CoroutineScope( SupervisorJob() + Dispatchers.Main )
    }

    private fun propagateViewEvents() {
        viewEvents().onEach { viewModel.onEvent(it) }
                .launchIn(lifecycleScope)
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewModel.actionFlow()
                .filter { scope?.isActive ?: false }
                .onEach { handleAction(it) }
                .launchIn(lifecycleScope)
    }

    fun subscribe() {
        propagateViewEvents()
        subscribeToActions()
    }
}