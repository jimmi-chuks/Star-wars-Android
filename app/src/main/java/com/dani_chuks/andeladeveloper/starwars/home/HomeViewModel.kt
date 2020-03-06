package com.dani_chuks.andeladeveloper.starwars.home

import androidx.lifecycle.viewModelScope
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
import com.dani_chuks.andeladeveloper.starwars.base.mvi.MVIViewmodel
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.home.HomeEvent.*
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewAction.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
        private val interactor: HomeViewModelInteractor,
        override val dispatcherProvider: IDispatcherProvider)
    : MVIViewmodel<HomeState, HomeEvent, HomeViewAction>() {


    init {
        loadItemsLocal()
        updateItemsRemote()
    }

    private fun loadItemsLocal(){
        loadVehicles()
        loadSpecies()
        loadStarships()
        loadPlanets()
        loadPeople()
        loadFilms()
    }

    private fun updateItemsRemote(){
        remoteFilmsUpdate()
        remotePeopleUpdate()
        remotePlanetUpdate()
        remoteSpeciesUpdate()
        remoteVehiclesUpdate()
        remoteStarshipsUpdate()
    }

    override val initialState: HomeState = HomeState()

    override fun reduceState(state: HomeState, event: HomeEvent): HomeState {
        return when (event) {
            FetchAllItems -> {
                loadItemsLocal()
                updateItemsRemote()
                HomeState()
            }
            FetchAllMovieEvent -> {
                loadFilms()
                state.copy(movies = emptyList())
            }
            FetchAllPersonEvent -> {
                loadPeople()
                state.copy(people = emptyList())
            }
            FetchAllSpecieEvent -> {
                loadSpecies()
                state.copy(species = emptyList())
            }
            FetchAllPlanetEvent -> {
                loadPlanets()
                state.copy(planets = emptyList())
            }
            FetchAllVehicleEvent -> {
                loadVehicles()
                state.copy(vehicles = emptyList())
            }
            FetchAllStarShipEvent -> {
                loadStarships()
                state.copy(starships = emptyList())
            }
            ShowAllMoviesEvent -> {
                onAction(ShowAllMoviesAction)
                state
            }
            ShowAllPeopleEvent -> {
                onAction(ShowAllPeopleAction)
                state
            }
            ShowAllSpeciesEvent -> {
                onAction(ShowAllSpeciesAction)
                state
            }
            ShowAllPlanetsEvent -> {
                onAction(ShowAllPlanetsAction)
                state
            }
            ShowAllVehiclesEvent -> {
                onAction(ShowAllVehiclesAction)
                state
            }
            ShowAllStarShipsEvent -> {
                onAction(ShowAllStarShipsAction)
                state
            }
            is ShowMovieEvent -> {
                onAction(ShowMovieAction(event.movieURL))
                state
            }
            is ShowPersonEvent -> {
                onAction(ShowPersonAction(event.personURL))
                state
            }
            is ShowSpecieEvent -> {
                onAction(ShowSpecieAction(event.specieURL))
                state
            }
            is ShowPlanetEvent -> {
                onAction(ShowPlanetAction(event.planetURL))
                state
            }
            is ShowVehicleEvent -> {
                onAction(ShowVehicleAction(event.vehicleURL))
                state
            }
            is ShowStarShipEvent -> {
                onAction(ShowStarShipAction(event.starShipURL))
                state
            }
            is MoviesFetchedEvent -> {
                state.copy(movies = event.movies)
            }
            is PeopleFetchedEvent -> {
                state.copy(people = event.people)
            }
            is SpeciesFetchedEvent -> {
                state.copy(species = event.species)
            }
            is PlanetsFetchedEvent -> {
                state.copy(planets = event.planets)
            }
            is VehiclesFetchedEvent -> {
                state.copy(vehicles = event.vehicles)
            }
            is StarShipsFetchedEvent -> {
                state.copy(starships = event.starships)
            }
        }
    }

    override fun getIoContext(): CoroutineContext  = dispatcherProvider.io + exceptionHandler


    private fun loadFilms() {
        viewModelScope.launch(dispatcherProvider.io) {
            interactor.loadFilms()
                    .collect {
                        it?.let { Mapper.mapFims(it)?.let { onEvent(MoviesFetchedEvent(it)) } }
                    }
        }
    }

    private fun loadPeople() =
            viewModelScope.launch(dispatcherProvider.io) {
                interactor.loadPeople()
                        .collect {
                            it?.let {
                                Mapper.mapPeople(it)?.let{ onEvent(PeopleFetchedEvent(it)) }
                            }
                        }
            }


    private fun loadPlanets() {
        viewModelScope.launch(dispatcherProvider.io + exceptionHandler) {
            interactor.loadPlanets()
                    .collect {
                        it?.let{
                            Mapper.mapPlanets(it)?.let{ onEvent(PlanetsFetchedEvent(it)) }
                        }
                    }
        }
    }

    private fun loadSpecies(){
        viewModelScope.launch(dispatcherProvider.io) {
            interactor.loadSpecies()
                    .collect {
                        it?.let{
                            Mapper.mapSpecies(it)?.let { onEvent(SpeciesFetchedEvent(it)) }
                        }
                    }
        }
    }

    private fun loadVehicles() {
        viewModelScope.launch(dispatcherProvider.io) {
            interactor.loadVehicles()
                    .collect {
                        it?.let {
                            Mapper.mapVehicles(it)?.let{ onEvent(VehiclesFetchedEvent(it))}
                        }
                    }
        }
    }

    private fun loadStarships() {
        viewModelScope.launch(dispatcherProvider.io) {
            interactor.loadStarships()
                    .collect {
                        it?.let{
                            Mapper.mapStarships(it)?.let { onEvent(StarShipsFetchedEvent(it)) }
                        }
                    }
        }
    }

    private fun remoteFilmsUpdate() =
            viewModelScope.launch(dispatcherProvider.io) {
                val fetchedFilms = interactor.loadFilmsRemote()
                if (fetchedFilms is Result.Error) {
                    withContext(dispatcherProvider.main) {
                        println("Error fetching films")
                        println(fetchedFilms)
                    }
                }
            }

    private fun remotePeopleUpdate() {
        viewModelScope.launch(dispatcherProvider.io) {
            val peopleFromRemote = interactor.loadPeopleRemote(firstPage)
            if (peopleFromRemote is Result.Error) printError(peopleFromRemote.exception, "People")
        }
    }

    private fun remotePlanetUpdate() {
        viewModelScope.launch(dispatcherProvider.io) {
            val planetFromRemote = interactor.loadPlanetRemote(firstPage)
            if (planetFromRemote is Result.Error) printError(planetFromRemote.exception, "Planets")
        }
    }

    private fun remoteSpeciesUpdate() {
        viewModelScope.launch(dispatcherProvider.io) {
            val speciesFromRemote = interactor.loadSpeciesRemote(firstPage)
            if (speciesFromRemote is Result.Error) printError(speciesFromRemote.exception, "Species")
        }
    }

    private fun remoteVehiclesUpdate() {
        viewModelScope.launch(dispatcherProvider.io) {
            val vehiclesFromRemote = interactor.loadVehicleRemote(firstPage)
            if (vehiclesFromRemote is Result.Error) printError(vehiclesFromRemote.exception, "Vehicle")
        }
    }

    private fun remoteStarshipsUpdate() {
        viewModelScope.launch(dispatcherProvider.io) {
            val starshipsFromRemote = interactor.loadStarshipsRemote(firstPage)
            if (starshipsFromRemote is Result.Error)
                printError(starshipsFromRemote.exception, "Starship")
        }
    }

    val  printError = { exception: Exception, message: String ->
        println("Error fetching $message: ${exception.message}")
    }

    companion object {
        const val firstPage = 1
    }
}
