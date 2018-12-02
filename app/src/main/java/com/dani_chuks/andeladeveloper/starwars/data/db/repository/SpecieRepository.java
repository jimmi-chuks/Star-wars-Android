package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class SpecieRepository implements DataSource<Specie>{

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
    public SpecieRepository(@NonNull final ApiService apiService,
                            @NonNull final AppDatabase appDatabase,
                            @NonNull final ISchedulerProvider schedulerProvider,
                            final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Flowable<List<Specie>> getAll() {
        fetchIfEmpty();
        return appDatabase.specieDao().getAll();
    }

    @Override
    public Flowable<List<Specie>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.specieDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Specie>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.specieDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Specie> getItemByUrl(final String stringUrl) {
        int specieId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getSpecieById(specieId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(specie -> appDatabase.specieDao().insertSpecie(specie)));

        return appDatabase.specieDao().getSpecieByUrl(stringUrl);
    }

    @Override
    public void insertItem(final Specie specie) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.specieDao().insertSpecie(specie)));
    }

    @Override
    public void insertItemList(final List<Specie> species) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.specieDao().insertSpecieList(species)));
    }

    private void fetchIfEmpty() {
        if (preferenceManager.isDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME)) {
            final int firstPage = 1;
            disposableManager.add(
                    apiService.getSpecieList(firstPage)
                            .subscribeOn(schedulerProvider.getIoScheduler())
                            .subscribe(response ->
                            {
                                appDatabase.specieDao().insertSpecieList(response.getSpecie());
                                preferenceManager.setResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME, (firstPage + 1));
                                preferenceManager.setDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME);
                            }));
        }
    }
}
