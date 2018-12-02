package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class PersonRepository implements DataSource<Person> {

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
    public PersonRepository(@NonNull final ApiService apiService,
                            @NonNull final AppDatabase appDatabase,
                            @NonNull final ISchedulerProvider schedulerProvider,
                            final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Flowable<List<Person>> getAll() {
        fetchIfEmpty();
        return appDatabase.personDao().getAll();
    }

    @Override
    public Flowable<List<Person>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.personDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Person>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.personDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Person> getItemByUrl(final String stringUrl) {
        int personId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getPersonById(personId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(person -> appDatabase.personDao().insertPerson(person)));

        return appDatabase.personDao().getPersonByURL(stringUrl);
    }

    @Override
    public void insertItem(final Person person) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.personDao().insertPerson(person)));
    }

    @Override
    public void insertItemList(final List<Person> people) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.personDao().insertPeople(people)));
    }

    private void fetchIfEmpty() {
        if (preferenceManager.isDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME)) {
            final int firstPage = 1;
            disposableManager.add(
                    apiService.getPeople(firstPage)
                            .subscribeOn(schedulerProvider.getIoScheduler())
                            .subscribe(response ->
                            {
                                appDatabase.personDao().insertPeople(response.getPerson());
                                preferenceManager.setResourceNextPage(AppConstants.PERSON_RESOURCE_NAME, (firstPage + 1));
                                preferenceManager.setDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME);
                            }));
        }
    }
}
