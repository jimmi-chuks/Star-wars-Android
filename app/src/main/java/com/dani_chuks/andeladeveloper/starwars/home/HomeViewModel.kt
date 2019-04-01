package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.MutableLiveData
import com.dani_chuks.andeladeveloper.presentation_models.*
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.BaseViewModel
import com.dani_chuks.andeladeveloper.starwars.dagger.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.dagger.Result
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import javax.inject.Inject

class HomeViewModel @Inject
constructor(private val interactor: HomeViewModelInteractor,
            val iDispatchersProvider: IDispatcherProvider) : BaseViewModel(iDispatchersProvider) {

    override val job: Job = Job()
    override val scope: CoroutineScope =  CoroutineScope(iDispatchersProvider.main + job)

    var films: MutableLiveData<List<FilmModel>> = MutableLiveData()
    var people: MutableLiveData<List<PersonModel>> = MutableLiveData()
    var planets: MutableLiveData<List<PlanetModel>> = MutableLiveData()
    var species: MutableLiveData<List<SpecieModel>> = MutableLiveData()
    var starships: MutableLiveData<List<StarshipModel>> = MutableLiveData()
    var vehicles: MutableLiveData<List<VehicleModel>> = MutableLiveData()
    val disposableManager = CompositeDisposable()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

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

        uiScope.coroutineContext.cancelChildren()
    }

    private fun loadFilms() {
//        val result = storiesRepository.loadStories(page)
//        parentJobs.remove(jobId)
        uiScope.launch(iDispatchersProvider.io){
            val fetchedFilms = interactor.loadFilms()
            fetchedFilms?.let { films.postValue(Mapper.mapFims(it)) }
        }

        uiScope.launch(iDispatchersProvider.io){
            val fetchedFilms = interactor.loadFilmsRemote()
            if(fetchedFilms is Result.Error){
                withContext(iDispatchersProvider.main /**+ job */){
                    println("Error fetching films")
                    println(fetchedFilms)
                }
            }
        }
    }

    private fun loadPeople() {
        uiScope.launch(iDispatchersProvider.io) {
            val peopleFetched = interactor.loadPeople()
            peopleFetched?.let {people.postValue(Mapper.mapPeople(it))}
        }

        uiScope.launch(iDispatchersProvider.io) {
            val peopleFromRemote = interactor.loadPeopleRemote(firstPage)
            if(peopleFromRemote is Result.Error){
                println("Error fetching people")
                println(peopleFromRemote)
            }
        }
    }

    private fun loadPlanets() {
        uiScope.launch(iDispatchersProvider.io) {
            val planetList = interactor.loadPlanets()
            planetList?.let {planets.postValue(Mapper.mapPlanets(it))}
        }

        uiScope.launch(iDispatchersProvider.io) {
            val planetFromRemote = interactor.loadPlanetRemote(firstPage)
            if(planetFromRemote is Result.Error){
                println("Error fetching planets")
                println(planetFromRemote)
            }
        }
    }

    private fun loadSpecies() {
        uiScope.launch(iDispatchersProvider.io) {
            val specieList = interactor.loadSpecies()
            specieList?.let {species.postValue(Mapper.mapSpecies(it))}
        }

        uiScope.launch(iDispatchersProvider.io) {
            val speciesFromRemote = interactor.loadSpeciesRemote(firstPage)
            if(speciesFromRemote is Result.Error){
                println("Error fetching species")
                println(speciesFromRemote)
            }
        }
    }

    private fun loadVehicles() {
        uiScope.launch(iDispatchersProvider.io) {
            val vehicleList = interactor.loadVehicles()
            vehicleList?.let {vehicles.postValue(Mapper.mapVehicles(it))}
        }

        uiScope.launch(iDispatchersProvider.io) {
            val vehiclesFromRemote = interactor.loadVehicleRemote(firstPage)
            if(vehiclesFromRemote is Result.Error){
                println("Error fetching vehicles")
                println(vehiclesFromRemote)
            }
        }
    }

    private fun loadStarships() {
        uiScope.launch(iDispatchersProvider.io) {
            val starshipList = interactor.loadStarships()
            starshipList?.let {starships.postValue(Mapper.mapStarships(it))}
        }

        uiScope.launch(iDispatchersProvider.io) {
            val starshipsFromRemote = interactor.loadStarshipsRemote(firstPage)
            if(starshipsFromRemote is Result.Error){
                println("Error fetching starships")
                println(starshipsFromRemote)
            }
        }
    }

    companion object {
        const val firstPage = 1
    }

}
