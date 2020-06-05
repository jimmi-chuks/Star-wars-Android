package com.dani_chuks.andeladeveloper.starwars.base.mvi

import android.os.Bundle
import android.os.PersistableBundle
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

    private fun subscribeToViewEvents() {
        val mn = SupervisorJob() + Dispatchers.IO
        val sc = CoroutineScope(mn)
        viewEvents().onEach { viewModel.onEvent(it) }
                .launchIn(lifecycleScope)
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewModel.actionFlow()
                .onEach { handleAction(it) }
                .launchIn(lifecycleScope)
    }

    fun subscribe() {
        subscribeToViewEvents()
        subscribeToActions()
    }
}