package com.dani_chuks.andeladeveloper.starwars.base.mvi

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIFragment: Fragment() {

    abstract var viewModel: MVIViewmodel

    abstract fun viewEvents(): Flow<*>

    abstract fun handleAction(action: MVIAction)

    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
//        viewModel.initState()
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEventAndAction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private fun subscribeToViewEvents() {
        viewEvents().filterIsInstance<MVIEvent>()
                .onEach { viewModel.onEvent(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewModel.actionFlow()
                .filter { lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) || it.important}
                .onEach { handleAction(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun subscribeToEventAndAction() {
        subscribeToViewEvents()
        subscribeToActions()
    }
}