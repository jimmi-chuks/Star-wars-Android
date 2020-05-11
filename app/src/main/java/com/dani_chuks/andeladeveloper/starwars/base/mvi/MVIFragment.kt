package com.dani_chuks.andeladeveloper.starwars.base.mvi

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class MVIFragment<S, E, A, V : MVIViewmodel<S, E, A>> : Fragment() {

    abstract var viewModel: V

    abstract fun viewEvents(): Flow<E>

    abstract fun handleAction(action: A)

    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        viewModel.initState()
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
        viewEvents()
                .onEach { viewModel.onEvent(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    @FlowPreview
    private fun subscribeToActions() {
        viewModel.actionChannel.asFlow()
                .onEach { handleAction(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun subscribeToEventAndAction() {
        subscribeToViewEvents()
        subscribeToActions()
    }
}