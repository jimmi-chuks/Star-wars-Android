package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*


data class HomeState(
        val initialLoading: Boolean = true,
        val movies: List<FilmModel> = emptyList(),
        val people: List<PersonModel> = emptyList(),
        val species: List<SpecieModel> = emptyList(),
        val planets: List<PlanetModel> = emptyList(),
        val vehicles: List<VehicleModel> = emptyList(),
        val starships: List<StarshipModel> = emptyList()
)

sealed class HomeViewAction{
    object ShowAllMoviesAction: HomeViewAction()
    object ShowAllPeopleAction: HomeViewAction()
    object ShowAllSpeciesAction: HomeViewAction()
    object ShowAllPlanetsAction: HomeViewAction()
    object ShowAllVehiclesAction: HomeViewAction()
    object ShowAllStarShipsAction: HomeViewAction()

    data class ShowMovieAction(val movieURL: String): HomeViewAction()
    data class ShowPersonAction(val personURL: String): HomeViewAction()
    data class ShowSpecieAction(val specieURL: String): HomeViewAction()
    data class ShowPlanetAction(val planetURL: String): HomeViewAction()
    data class ShowVehicleAction(val vehicleURL: String): HomeViewAction()
    data class ShowStarShipAction(val starShipURL: String): HomeViewAction()
}


sealed class HomeEvent{
    object FetchAllItems: HomeEvent()
    object FetchAllMovieEvent: HomeEvent()
    object FetchAllPersonEvent: HomeEvent()
    object FetchAllSpecieEvent: HomeEvent()
    object FetchAllPlanetEvent: HomeEvent()
    object FetchAllVehicleEvent: HomeEvent()
    object FetchAllStarShipEvent: HomeEvent()

    object ShowAllMoviesEvent: HomeEvent()
    object ShowAllPeopleEvent: HomeEvent()
    object ShowAllSpeciesEvent: HomeEvent()
    object ShowAllPlanetsEvent: HomeEvent()
    object ShowAllVehiclesEvent: HomeEvent()
    object ShowAllStarShipsEvent: HomeEvent()

    data class ShowMovieEvent(val movieURL: String): HomeEvent()
    data class ShowPersonEvent(val personURL: String): HomeEvent()
    data class ShowSpecieEvent(val specieURL: String): HomeEvent()
    data class ShowPlanetEvent(val planetURL: String): HomeEvent()
    data class ShowVehicleEvent(val vehicleURL: String): HomeEvent()
    data class ShowStarShipEvent(val starShipURL: String): HomeEvent()

    data class MoviesFetchedEvent(val movies: List<FilmModel>): HomeEvent()
    data class PeopleFetchedEvent(val people: List<PersonModel>): HomeEvent()
    data class SpeciesFetchedEvent(val species: List<SpecieModel>): HomeEvent()
    data class PlanetsFetchedEvent(val planets: List<PlanetModel>): HomeEvent()
    data class VehiclesFetchedEvent(val vehicles: List<VehicleModel>): HomeEvent()
    data class StarShipsFetchedEvent(val starships: List<StarshipModel>): HomeEvent()
}