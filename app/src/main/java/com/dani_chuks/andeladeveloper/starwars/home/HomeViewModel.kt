package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.presentation_models.*
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject
constructor(private val interactor: HomeViewModelInteractor,
            val iDispatchersProvider: IDispatcherProvider) : ViewModel() {


    var films: MutableLiveData<List<FilmModel>> = MutableLiveData()
    var people: MutableLiveData<List<PersonModel>> = MutableLiveData()
    var planets: MutableLiveData<List<PlanetModel>> = MutableLiveData()
    var species: MutableLiveData<List<SpecieModel>> = MutableLiveData()
    var starships: MutableLiveData<List<StarshipModel>> = MutableLiveData()
    var vehicles: MutableLiveData<List<VehicleModel>> = MutableLiveData()


    init {
        loadVehicles()
        loadSpecies()
        loadStarships()
        loadPlanets()
        loadPeople()
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val fetchedFilms = interactor.loadFilms()
            films.postValue(Mapper.mapFims(fetchedFilms))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val fetchedFilms = interactor.loadFilmsRemote()
            if (fetchedFilms is Result.Error) {
                withContext(iDispatchersProvider.main) {
                    println("Error fetching films")
                    println(fetchedFilms)
                }
            }
        }
    }

    private fun loadPeople() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val peopleFetched = interactor.loadPeople()
            people.postValue(Mapper.mapPeople(peopleFetched))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val peopleFromRemote = interactor.loadPeopleRemote(firstPage)
            if (peopleFromRemote is Result.Error) {
                println("Error fetching people")
                println(peopleFromRemote)
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val planetList = interactor.loadPlanets()
            if(planetList.isNotEmpty()) planets.postValue(Mapper.mapPlanets(planetList))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val planetFromRemote = interactor.loadPlanetRemote(firstPage)
            if (planetFromRemote is Result.Error) {
                println("Error fetching planets")
                println(planetFromRemote)
            }
        }
    }

    private fun loadSpecies() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val specieList = interactor.loadSpecies()
            if (specieList.isNotEmpty()) species.postValue(Mapper.mapSpecies(specieList))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val speciesFromRemote = interactor.loadSpeciesRemote(firstPage)
            if (speciesFromRemote is Result.Error) {
                println("Error fetching species")
                println(speciesFromRemote)
            }
        }
    }

    private fun loadVehicles() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val vehicleList = interactor.loadVehicles()
            if (vehicleList.isNotEmpty()) vehicles.postValue(Mapper.mapVehicles(vehicleList))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val vehiclesFromRemote = interactor.loadVehicleRemote(firstPage)
            if (vehiclesFromRemote is Result.Error) {
                println("Error fetching vehicles")
                println(vehiclesFromRemote)
            }
        }
    }

    private fun loadStarships() {
        viewModelScope.launch(iDispatchersProvider.io) {
            val starshipList = interactor.loadStarships()
            if (starshipList.isNotEmpty()) starships.postValue(Mapper.mapStarships(starshipList))
        }

        viewModelScope.launch(iDispatchersProvider.io) {
            val starshipsFromRemote = interactor.loadStarshipsRemote(firstPage)
            if (starshipsFromRemote is Result.Error) {
                println("Error fetching starships")
                println(starshipsFromRemote)
            }
        }
    }

    companion object {
        const val firstPage = 1
    }

}
