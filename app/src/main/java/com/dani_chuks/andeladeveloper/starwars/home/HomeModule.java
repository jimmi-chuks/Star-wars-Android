package com.dani_chuks.andeladeveloper.starwars.home;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class HomeModule {

    @Provides
    @Reusable
    HomeViewModelFactory providesHomeViewMOdelFactory(@NonNull final AppDatabase appDatabase, @NonNull final ISchedulerProvider iSchedulerProvider){
        return  new HomeViewModelFactory(appDatabase, iSchedulerProvider);
    }

    @Provides
    @Reusable
    IHomePresenter providesHomePresenter(@NonNull final SharedPreferenceManager preferenceManager,
                                        @NonNull final ISchedulerProvider schedulerProvider,
                                        @NonNull final ApiService apiService, @NonNull final AppDatabase appDatabase){
        return new HomePresenter(preferenceManager, schedulerProvider, apiService, appDatabase);
    }
}
