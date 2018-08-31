package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModelInteractor;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final HomeViewModelInteractor viewModelInteractor;

    public HomeViewModelFactory(@NonNull final HomeViewModelInteractor viewModelInteractor,
                                @NonNull final ISchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.viewModelInteractor = viewModelInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(viewModelInteractor, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
