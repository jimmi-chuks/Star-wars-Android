package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.presentation_models.PersonModel;
import com.dani_chuks.andeladeveloper.presentation_models.PlanetModel;
import com.dani_chuks.andeladeveloper.presentation_models.SpecieModel;
import com.dani_chuks.andeladeveloper.presentation_models.StarshipModel;
import com.dani_chuks.andeladeveloper.presentation_models.VehicleModel;
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper;
import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends ViewModel {

    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final HomeViewModelInteractor interactor;
    public MutableLiveData<List<FilmModel>> films = new MutableLiveData<>();
    public MutableLiveData<List<PersonModel>> people = new MutableLiveData<>();
    public MutableLiveData<List<PlanetModel>> planets = new MutableLiveData<>();
    public MutableLiveData<List<SpecieModel>> species = new MutableLiveData<>();
    public MutableLiveData<List<StarshipModel>> starships = new MutableLiveData<>();
    public MutableLiveData<List<VehicleModel>> vehicles = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    public HomeViewModel(@NonNull final HomeViewModelInteractor interactor,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    public void initAll(){
        loadVehicles();
        loadSpecies();
        loadStarships();
        loadPlanets();
//        loadPeople();
        loadFilms();
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
                        .subscribe(filmList -> {
                            ArrayList<FilmModel> fm = Mapper.mapFims(filmList);
                            films.setValue(fm);
                        }));
    }

    private void loadPeople() {
        disposableManager.add(
                interactor.loadPeople()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(personList -> {
                            ArrayList<PersonModel> fm = Mapper.mapPeople(personList);
                            people.setValue(fm);
                        }));
    }

    private void loadPlanets() {
        disposableManager.add(
                interactor.loadPlanets()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(planetList -> {
                            ArrayList<PlanetModel> fm = Mapper.mapPlanets(planetList);
                            planets.setValue(fm);
                        }));
    }

    private void loadSpecies() {
        disposableManager.add(
                interactor.loadSpecies()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(specieListList -> {
                            ArrayList<SpecieModel> fm = Mapper.mapSpecies(specieListList);
                            species.setValue(fm);
                        }));
    }

    private void loadVehicles() {
        disposableManager.add(
                interactor.loadVehicles()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(vehicleListnList -> {
                            ArrayList<VehicleModel> fm = Mapper.mapVehicles(vehicleListnList);
                            vehicles.setValue(fm);
                        }));
    }

    private void loadStarships() {
        disposableManager.add(
                interactor.loadStarships()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(starshipList -> {
                            ArrayList<StarshipModel> fm = Mapper.mapStarships(starshipList);
                            starships.setValue(fm);
                        }));
    }

}
