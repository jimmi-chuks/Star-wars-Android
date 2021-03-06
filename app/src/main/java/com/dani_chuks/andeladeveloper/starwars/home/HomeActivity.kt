package com.dani_chuks.andeladeveloper.starwars.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dani_chuks.andeladeveloper.presentation_models.ItemModelType
import com.dani_chuks.andeladeveloper.starwars.R
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIActivity
import com.dani_chuks.andeladeveloper.starwars.base.mvi.clicks
import com.dani_chuks.andeladeveloper.starwars.databinding.ActivityHomeBinding
import com.dani_chuks.andeladeveloper.starwars.util.DialogBuilder
import dagger.android.AndroidInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
class HomeActivity : MVIActivity<HomeState, HomeEvent, HomeViewAction, HomeViewModel>() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    override lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.initState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        subscribe()
        viewModel.onEvent(HomeEvent.InitEvents)
    }

    override fun viewEvents(): Flow<HomeEvent> {
        val flows = listOf(
                binding.showAllStarshipsButton.clicks().map { HomeEvent.ShowAllEvent(ItemModelType.STARSHIP) }
        )
        return flows.asFlow().flattenMerge(flows.size)
    }

    override fun handleAction(action: HomeViewAction) {
        Log.e("PLOPP", " received Action: ${action.toString()}")
//        buildDialog()
    }

    fun buildDialog(){
        DialogBuilder(this)
                .setMessage("Testing")
                .setCancellable(false)
                .setPositiveAction("Ok") { Log.e("PLOPP", " positive button clicked") }
                .setNegativeAction("Close"){ Log.e("PLOPP", " Negative button clicked") }
                .setDialogDismissListener { Log.e("PLOPP", "Dialog Dismissed") }
                .build()
                .show()
    }
}
