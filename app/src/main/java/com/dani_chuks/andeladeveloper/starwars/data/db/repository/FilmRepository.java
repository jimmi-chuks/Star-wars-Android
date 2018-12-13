package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class FilmRepository implements DataSource<Film> {

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
    public FilmRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                          @NonNull final ISchedulerProvider schedulerProvider,
                          @NonNull final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    public Flowable<Film> getFilmByUrl(final String filmUrl) {
        int url = DbUtils.getLastPathFromUrl(filmUrl);
        disposableManager.add(
                apiService.getFilmById(url)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(this::insertItem));

        return appDatabase.filmDao().getFilmByUrl(filmUrl);
    }

    @Override
    public Flowable<List<Film>> getAll() {
        fetchIfEmpty();
        return appDatabase.filmDao().getAll();
    }

    @Override
    public Flowable<List<Film>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.filmDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Film>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.filmDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Film> getItemByUrl(final String stringUrl) {
        int filmId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getFilmById(filmId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(appDatabase.filmDao()::insertFilm));

        return appDatabase.filmDao().
                getFilmByUrlAsSingle(stringUrl);
    }

    @Override
    public void insertItem(final Film film) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.filmDao().insertFilm(film)));
    }

    @Override
    public void insertItemList(final List<Film> t) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.filmDao().insertFilms(t)));
    }

    private void fetchIfEmpty() {
//        if (preferenceManager.isDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)) {
//            disposableManager.add(
//                    apiService.getAllFilms()
//                            .subscribeOn(schedulerProvider.getIoScheduler())
//                            .subscribe(response -> {
//                                appDatabase.filmDao().insertFilms(response.getFilm());
//                                preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME);
//                            }));
//        }
        disposableManager.add(
                apiService.getAllFilms()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response -> {
                            appDatabase.filmDao().insertFilms(response.getFilm());
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME);
                        }));
    }

}
