package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends ViewModel {

    @NonNull
    final ISchedulerProvider schedulerProvider;
    @NonNull
    private final AppDatabase appDatabase;
    private MutableLiveData<List<Film>> films = new MutableLiveData<>();
    private MutableLiveData<List<Person>> people = new MutableLiveData<>();
    private MutableLiveData<List<Planet>> planets = new MutableLiveData<>();
    private MutableLiveData<List<Specie>> species = new MutableLiveData<>();
    private MutableLiveData<List<Starship>> starships = new MutableLiveData<>();
    private MutableLiveData<List<Vehicle>> vehicles = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    public HomeViewModel(@NonNull final AppDatabase appDatabase,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
    }

    public MutableLiveData<List<Film>> getFilms() {
        if (films.getValue() == null) {
            loadFilms();
        }
        return films;
    }

    public MutableLiveData<List<Person>> getPeople() {
        if (films.getValue() == null) {
            loadPeople();
        }
        return people;
    }

    public MutableLiveData<List<Planet>> getPlanets() {
        if (films.getValue() == null) {
            loadPlanets();
        }
        return planets;
    }

    public MutableLiveData<List<Vehicle>> getVehicles() {
        if (films.getValue() == null) {
            loadVehicles();
        }
        return vehicles;
    }

    public MutableLiveData<List<Specie>> getSpecies() {
        if (films.getValue() == null) {
            loadSpecies();
        }
        return species;
    }

    public MutableLiveData<List<Starship>> getStarships() {
        if (films.getValue() == null) {
            loadStarships();
        }
        return starships;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableManager.dispose();
    }

    private void loadFilms() {
        disposableManager.add(
                appDatabase.filmDao()
                        .getFirstSeven()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.films.setValue(response)));
    }

    private void loadPeople() {
        disposableManager.add(
                appDatabase.personDao()
                        .getFirstSeven()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.people.setValue(response))
        );
    }

    private void loadPlanets() {
        disposableManager.add(
                appDatabase.planetDao()
                        .getFirstSeven()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.planets.setValue(response))
        );
    }

    private void loadSpecies() {
        disposableManager.add(
                appDatabase.specieDao()
                        .getAll()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.species.setValue(response))
        );
    }

    private void loadVehicles() {
        disposableManager.add(
                appDatabase.vehicleDao()
                        .getFirstSeven()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.vehicles.setValue(response))
        );
    }

    private void loadStarships() {
        disposableManager.add(
                appDatabase.starshipDao()
                        .getFirstSeven()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(response -> this.starships.setValue(response))
        );
    }

}
