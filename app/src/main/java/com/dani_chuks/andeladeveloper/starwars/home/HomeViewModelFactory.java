package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    final ISchedulerProvider schedulerProvider;
    @NonNull
    private final AppDatabase appDatabase;

    public HomeViewModelFactory(@NonNull final AppDatabase appDatabase,
                                @NonNull final ISchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.appDatabase = appDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(appDatabase, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
