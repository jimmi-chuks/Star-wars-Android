package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class StarshipRepository implements DataSource<Starship>{

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
    public StarshipRepository(@NonNull final ApiService apiService,
                              @NonNull final AppDatabase appDatabase,
                              @NonNull final ISchedulerProvider schedulerProvider,
                              final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Flowable<List<Starship>> getAll() {
        fetchIfEmpty();
        return appDatabase.starshipDao().getAll();
    }

    @Override
    public Flowable<List<Starship>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.starshipDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Starship>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.starshipDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Starship> getItemByUrl(final String stringUrl) {
        int starshipId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getStarshipById(starshipId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(starship -> appDatabase.starshipDao().insertStarship(starship)));

        return appDatabase.starshipDao().getStarshipByURL(stringUrl);
    }

    @Override
    public void insertItem(final Starship starship) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.starshipDao().insertStarship(starship)));
    }

    @Override
    public void insertItemList(final List<Starship> starship) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.starshipDao().insertStarshipList(starship)));
    }

    private void fetchIfEmpty() {
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME)) {
            int nextPage = preferenceManager.getResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME);
            final int newNextPage = nextPage + 1;
            disposableManager.add(
                    apiService.getStarshipList(nextPage)
                            .subscribeOn(schedulerProvider.getIoScheduler())
                            .subscribe(response ->
                            {
                                appDatabase.starshipDao().insertStarshipList(response.getStarship());
                                preferenceManager.setResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME, newNextPage);
                                preferenceManager.setDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME, true);
                            }));
        }
    }
}
