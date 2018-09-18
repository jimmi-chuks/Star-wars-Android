package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends ViewModel {

    @NonNull
    final ISchedulerProvider schedulerProvider;
    @NonNull
    private final HomeViewModelInteractor interactor;
    private MutableLiveData<List<Film>> films = new MutableLiveData<>();
    private MutableLiveData<List<Person>> people = new MutableLiveData<>();
    private MutableLiveData<List<Planet>> planets = new MutableLiveData<>();
    private MutableLiveData<List<Specie>> species = new MutableLiveData<>();
    private MutableLiveData<List<Starship>> starships = new MutableLiveData<>();
    private MutableLiveData<List<Vehicle>> vehicles = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    public HomeViewModel(@NonNull final HomeViewModelInteractor interactor,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        this.interactor = interactor;
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
                interactor.loadFilms()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.films::setValue));
    }

    private void loadPeople() {
        disposableManager.add(
                interactor.loadPeople()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.people::setValue)
        );
    }

    private void loadPlanets() {
        disposableManager.add(
                interactor.loadPlanets()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.planets::setValue)
        );
    }

    private void loadSpecies() {
        disposableManager.add(
                interactor.loadSpecies()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.species::setValue)
        );
    }

    private void loadVehicles() {
        disposableManager.add(
                interactor.loadVehicles()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.vehicles::setValue)
        );
    }

    private void loadStarships() {
        disposableManager.add(
                interactor.loadStarships()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(this.starships::setValue)
        );
    }

}
