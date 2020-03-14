package com.dani_chuks.andeladeveloper.starwars.base.mvi

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIActivity<S, E, A, V : MVIViewmodel<S, E, A>> : AppCompatActivity() {

    abstract val viewModel: V

    abstract fun viewEvents(): Flow<E>

    abstract fun render(state: S)

    abstract fun handleAction(action: A)

    abstract fun initViewModel()

    private fun subscribeToState() {
        viewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun subscribeToViewEvents() {
        lifecycleScope.launch {
            viewEvents().collect { viewModel.onEvent(it) }
        }
    }

    @FlowPreview
    private fun subscribeToActions() {
        lifecycleScope.launch {
            viewModel.actionChannel
                    .asFlow()
                    .collect { handleAction(it) }
        }
    }

    fun subscribeToStateEventAndAction() {
        initViewModel()
        viewModel.initState()
        subscribeToState()
        subscribeToViewEvents()
        subscribeToActions()
    }
}