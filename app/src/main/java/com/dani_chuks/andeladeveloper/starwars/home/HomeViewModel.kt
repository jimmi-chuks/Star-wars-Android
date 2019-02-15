package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dani_chuks.andeladeveloper.presentation_models.*
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject
constructor(private val interactor: HomeViewModelInteractor, private val schedulerProvider: ISchedulerProvider) : ViewModel() {
    var films: MutableLiveData<List<FilmModel>> = MutableLiveData()
    var people: MutableLiveData<List<PersonModel>> = MutableLiveData()
    var planets: MutableLiveData<List<PlanetModel>> = MutableLiveData()
    var species: MutableLiveData<List<SpecieModel>> = MutableLiveData()
    var starships: MutableLiveData<List<StarshipModel>> = MutableLiveData()
    var vehicles: MutableLiveData<List<VehicleModel>> = MutableLiveData()
    val disposableManager = CompositeDisposable()

    init {
        loadVehicles()
        loadSpecies()
        loadStarships()
        loadPlanets()
        loadPeople()
        loadFilms()
    }

    override fun onCleared() {
        super.onCleared()
        disposableManager.dispose()
    }

    private fun loadFilms() {
        disposableManager.add(
                interactor.loadFilms()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe { films.setValue(Mapper.mapFims(it))})
    }

    private fun loadPeople() {
        disposableManager.add(
                interactor.loadPeople()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe {people.setValue(Mapper.mapPeople(it))})
    }

    private fun loadPlanets() {
        disposableManager.add(
                interactor.loadPlanets()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe { planets.setValue(Mapper.mapPlanets(it)) })
    }

    private fun loadSpecies() {
        disposableManager.add(
                interactor.loadSpecies()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe {species.setValue(Mapper.mapSpecies(it))})
    }

    private fun loadVehicles() {
        disposableManager.add(
                interactor.loadVehicles()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe {vehicles.setValue(Mapper.mapVehicles(it)) })
    }

    private fun loadStarships() {
        disposableManager.add(
                interactor.loadStarships()
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .observeOn(schedulerProvider.mainThreadScheduler)
                        .subscribe { starships.setValue(Mapper.mapStarships(it))})
    }

}
