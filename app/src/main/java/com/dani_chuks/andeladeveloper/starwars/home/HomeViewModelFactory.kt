package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
        private val viewModelInteractor: HomeViewModelInteractor,
        private val iDispatchersProvider: IDispatcherProvider)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(viewModelInteractor, iDispatchersProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
