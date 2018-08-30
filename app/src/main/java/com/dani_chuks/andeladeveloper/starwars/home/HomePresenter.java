package com.dani_chuks.andeladeveloper.starwars.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter implements IHomePresenter {

    @NonNull
    private final SharedPreferenceManager preferenceManager;
    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final AppDatabase appDatabase;
    @NonNull
    private
    ApiService apiService;
    @Nullable
    private
    HomeView homeView;
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    public HomePresenter(@NonNull final SharedPreferenceManager preferenceManager,
                         @NonNull final ISchedulerProvider schedulerProvider,
                         @NonNull final ApiService apiService,
                         @NonNull final AppDatabase appDatabase) {
        this.preferenceManager = preferenceManager;
        this.schedulerProvider = schedulerProvider;
        this.apiService = apiService;
        this.appDatabase = appDatabase;
    }

    @Override
    public void initView(final HomeView view) {
        this.homeView = view;
    }

    @Override
    public void fetchData() {
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)) {
            fetchFilmsData();
        }
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME)) {
            fetchPeopleData();
        }
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME)) {
            fetchPlanetData();
        }
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME)) {
            fetchSpecieData();
        }
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME)) {
            fetchStarshipData();
        }
        if (!preferenceManager.isDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME)) {
            fetchVehicleData();
        }
    }

    @Override
    public void addDisposable(final Disposable disposable) {
        disposableManager.add(disposable);
    }

    @Override
    public void clear() {
        disposableManager.clear();
    }

    private void fetchFilmsData() {
        addDisposable(
                apiService.getAllFilms()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response -> {
                            appDatabase.filmDao().insertFilms(response.getFilm());
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME, true);
                        })
        );
    }

    private void fetchPeopleData() {
        int nextPage = preferenceManager.getResourceNextPage(AppConstants.PERSON_RESOURCE_NAME);
        final int newNextPage = nextPage + 1;
        Log.e("HPre", "fetchPeopleData: ");
        addDisposable(
                apiService.getPeople(nextPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            for(Person p: response.getPerson()){
                                appDatabase.personDao().insertPerson(p);
                            }
                            appDatabase.personDao().insertPeople(response.getPerson());
                            preferenceManager.setResourceNextPage(AppConstants.PERSON_RESOURCE_NAME, newNextPage);
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME, true);
                        }));
    }

    private void fetchStarshipData() {
        int nextPage = preferenceManager.getResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME);
        Log.e("HPre", "fetchStarshipData: ");
        final int newNextPage = nextPage + 1;
        addDisposable(
                apiService.getStarshipList(nextPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            appDatabase.starshipDao().insertStarshipList(response.getStarship());
                            preferenceManager.setResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME, newNextPage);
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME, true);
                        }));
    }

    private void fetchVehicleData() {
        int nextPage = preferenceManager.getResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME);
        final int newNextPage = nextPage + 1;
        addDisposable(
                apiService.getVehicleList(nextPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            appDatabase.vehicleDao().insertVehicleList(response.getVehicle());
                            preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, newNextPage);
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME, true);
                        }));
    }

    private void fetchSpecieData() {
        int nextPage = preferenceManager.getResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME);
        final int newNextPage = nextPage + 1;
        addDisposable(
                apiService.getSpecieList(nextPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            appDatabase.specieDao().insertSpecie(response.getSpecie());
                            preferenceManager.setResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME, newNextPage);
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME, true);
                        }));
    }

    private void fetchPlanetData() {
        int nextPage = preferenceManager.getResourceNextPage(AppConstants.PLANET_RESOURCE_NAME);
        final int newNextPage = nextPage + 1;
        addDisposable(
                apiService.getPlanetList(nextPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            long[] num = appDatabase.planetDao().insertplanet(response.getPlanet());
                            preferenceManager.setResourceNextPage(AppConstants.PLANET_RESOURCE_NAME, newNextPage);
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME, true);

                        }));
    }


}
