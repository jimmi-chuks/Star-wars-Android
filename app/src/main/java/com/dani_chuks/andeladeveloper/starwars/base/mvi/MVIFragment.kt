package com.dani_chuks.andeladeveloper.starwars.base.mvi

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIFragment<S, E, A, V : MVIViewmodel<S, E, A>> : Fragment() {

    abstract var viewModel: V

    abstract fun viewEvents(): Flow<E>

    abstract fun render(state: S)

    abstract fun handleAction(action: A)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToStateEventAndAction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private fun subscribeToState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun subscribeToViewEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewEvents().collect { viewModel.onEvent(it) }
        }
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.actionChannel.asFlow()
                    .collect { handleAction(it) }
        }
    }

    private fun subscribeToStateEventAndAction() {
        viewModel.initState()
        subscribeToState()
        subscribeToViewEvents()
        subscribeToActions()
    }
}