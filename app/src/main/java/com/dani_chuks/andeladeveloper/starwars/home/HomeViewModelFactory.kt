package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(private val viewModelInteractor: HomeViewModelInteractor,
                                               private val iDispatchersProvider: IDispatcherProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(viewModelInteractor, iDispatchersProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
