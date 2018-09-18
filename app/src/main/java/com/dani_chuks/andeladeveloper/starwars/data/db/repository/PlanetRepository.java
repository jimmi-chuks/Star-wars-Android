package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class PlanetRepository implements DataSource<Planet>{

    @NonNull
    private final ApiService apiService;
    @NonNull
    private final AppDatabase appDatabase;
    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final CompositeDisposable disposableManager = new CompositeDisposable();
    private final SharedPreferenceManager preferenceManager;

    @Inject
    public PlanetRepository(@NonNull final ApiService apiService,
                            @NonNull final AppDatabase appDatabase,
                            @NonNull final ISchedulerProvider schedulerProvider,
                            final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Flowable<List<Planet>> getAll() {
        fetchIfEmpty();
        return appDatabase.planetDao().getAll();
    }

    @Override
    public Flowable<List<Planet>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.planetDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Planet>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.planetDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Planet> getItemByUrl(final String stringUrl) {
        int personId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getPlanetById(personId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(film -> appDatabase.planetDao().insertPlanet(film)));

        return appDatabase.planetDao().getplanetByURL(stringUrl);
    }

    @Override
    public void insertItem(final Planet planet) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.planetDao().insertPlanet(planet)));
    }

    @Override
    public void insertItemList(final List<Planet> planets) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.planetDao().insertPlanetList(planets)));
    }

    private void fetchIfEmpty() {
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME)) {
            int nextPage = preferenceManager.getResourceNextPage(AppConstants.PLANET_RESOURCE_NAME);
            final int newNextPage = nextPage + 1;
            disposableManager.add(
                    apiService.getPlanetList(nextPage)
                            .subscribeOn(schedulerProvider.getIoScheduler())
                            .subscribe(response ->
                            {
                                appDatabase.planetDao().insertPlanetList(response.getPlanet());
                                preferenceManager.setResourceNextPage(AppConstants.PLANET_RESOURCE_NAME, newNextPage);
                                preferenceManager.setDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME, true);
                            }));
        }
    }
}