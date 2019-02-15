package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val viewModelInteractor: HomeViewModelInteractor,
                           private val schedulerProvider: ISchedulerProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) return HomeViewModel(viewModelInteractor, schedulerProvider) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
