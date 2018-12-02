package com.dani_chuks.andeladeveloper.starwars.home;

import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.presentation_models.FilmModel;
import com.dani_chuks.andeladeveloper.presentation_models.PersonModel;
import com.dani_chuks.andeladeveloper.presentation_models.PlanetModel;
import com.dani_chuks.andeladeveloper.presentation_models.SpecieModel;
import com.dani_chuks.andeladeveloper.presentation_models.StarshipModel;
import com.dani_chuks.andeladeveloper.presentation_models.VehicleModel;
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper;
import com.dani_chuks.andeladeveloper.starwars.BR;
import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends ViewModel implements Observable {

    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final HomeViewModelInteractor interactor;
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    public ObservableBoolean filmLoading = new ObservableBoolean();
    public ObservableBoolean planetLoading = new ObservableBoolean();
    public ObservableBoolean peopleLoading = new ObservableBoolean();
    public ObservableBoolean speciesLoading = new ObservableBoolean();
    public ObservableBoolean starshipLoading = new ObservableBoolean();
    public ObservableBoolean vehicleLoading = new ObservableBoolean();
    private List<FilmModel> films = new ArrayList<>();
    public List<PersonModel> people = new ArrayList<>();
    public List<PlanetModel> planets = new ArrayList<>();
    public List<SpecieModel> species = new ArrayList<>();
    public List<StarshipModel> starships = new ArrayList<>();
    public List<VehicleModel> vehicles = new ArrayList<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    public HomeViewModel(@NonNull final HomeViewModelInteractor interactor,
                         @NonNull final ISchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
        initAll();
    }

    @Override
    public void addOnPropertyChangedCallback(final OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(final OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    private void notifyPropertyChanged(int fieldId){
        callbacks.notifyCallbacks(this, fieldId, null);
    }

    public void initAll(){
        loadVehicles();
        loadSpecies();
        loadStarships();
        loadPlanets();
        loadPeople();
        loadFilms();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableManager.dispose();
    }

    @Bindable
    public List<FilmModel> getFilms() {
        return films;
    }

    @Bindable
    public List<PersonModel> getPeople() {
        return people;
    }

    @Bindable
    public List<PlanetModel> getPlanets() {
       return planets;
    }

    @Bindable
    public List<VehicleModel> getVehicles() {
        return vehicles;
    }

    @Bindable
    public List<SpecieModel> getSpecies() {
        return species;
    }

    @Bindable
    public List<StarshipModel> getStarships() {
        return starships;
    }


    private void loadFilms() {
        disposableManager.add(
                interactor.loadFilms()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(filmList -> {
                            ArrayList<FilmModel> fm = Mapper.mapFims(filmList);
                            films = fm;
                            filmLoading.set(false);
                            notifyPropertyChanged(BR.films);
                        }));
    }

    private void loadPeople() {
        disposableManager.add(
                interactor.loadPeople()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(personList -> {
                            ArrayList<PersonModel> fm = Mapper.mapPeople(personList);
                            people = fm;
                            peopleLoading.set(false);
                            notifyPropertyChanged(BR.people);
                        }));
    }

    private void loadPlanets() {
        disposableManager.add(
                interactor.loadPlanets()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(planetList -> {
                            ArrayList<PlanetModel> fm = Mapper.mapPlanets(planetList);
                            planets = fm;
                            planetLoading.set(false);
                            notifyPropertyChanged(BR.planets);
                        }));
    }

    private void loadSpecies() {
        disposableManager.add(
                interactor.loadSpecies()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(specieListList -> {
                            ArrayList<SpecieModel> fm = Mapper.mapSpecies(specieListList);
                            species = fm;
                            speciesLoading.set(false);
                            notifyPropertyChanged(BR.species);
                        }));
    }

    private void loadVehicles() {
        disposableManager.add(
                interactor.loadVehicles()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(vehicleListnList -> {
                            ArrayList<VehicleModel> fm = Mapper.mapVehicles(vehicleListnList);
                            vehicles = fm;
                            vehicleLoading.set(false);
                            notifyPropertyChanged(BR.vehicles);
                        }));
    }

    private void loadStarships() {
        disposableManager.add(
                interactor.loadStarships()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .observeOn(schedulerProvider.getMainThreadScheduler())
                        .subscribe(starshipList -> {
                            ArrayList<StarshipModel> fm = Mapper.mapStarships(starshipList);
                            starships = fm;
                            starshipLoading.set(false);
                            notifyPropertyChanged(BR.starships);
                        }));
    }

}
