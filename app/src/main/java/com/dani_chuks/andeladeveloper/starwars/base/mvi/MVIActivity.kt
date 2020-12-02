package com.dani_chuks.andeladeveloper.starwars.base.mvi

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIActivity : AppCompatActivity() {

    abstract val viewModel: MVIViewmodel

    abstract fun viewEvents(): Flow<*>

    abstract fun handleAction(action: MVIAction)

    abstract fun initViewModel()

    private fun propagateViewEvents() {
        viewEvents().filterIsInstance<MVIEvent>()
                .onEach { viewModel.onEvent(it) }
                .launchIn(lifecycleScope)
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewModel.actionFlow()
                .filter { lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) || it.important}
                .onEach { handleAction(MVIAction()) }
                .launchIn(lifecycleScope)
    }

    fun subscribe() {
        propagateViewEvents()
        subscribeToActions()
    }
}