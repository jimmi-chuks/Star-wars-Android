package com.dani_chuks.andeladeveloper.starwars.film

import com.dani_chuks.andeladeveloper.presentation_models.MainModels.*


data class FilmActivityState(
        val initialLoading: Boolean = true,
        val filmModel: FilmModel? = null,
        val people: List<PersonModel> = emptyList(),
        val species: List<SpecieModel> = emptyList(),
        val planets: List<PlanetModel> = emptyList(),
        val vehicles: List<VehicleModel> = emptyList(),
        val starships: List<StarshipModel> = emptyList()
)

sealed class FilmActivityActions {

    data class ShowMovieAction(val movieURL: String) : FilmActivityActions()
    data class ShowPersonAction(val personURL: String) : FilmActivityActions()
    data class ShowSpecieAction(val specieURL: String) : FilmActivityActions()
    data class ShowPlanetAction(val planetURL: String) : FilmActivityActions()
    data class ShowVehicleAction(val vehicleURL: String) : FilmActivityActions()
    data class ShowStarShipAction(val starShipURL: String) : FilmActivityActions()

    object CloseActivityAction : FilmActivityActions()

}

sealed class FilmActivityEvents {

    data class FetchFilm(val filmURL: String) : FilmActivityEvents()

    data class ShowMovieEvent(val movieURL: String) : FilmActivityEvents()
    data class ShowPersonEvent(val personURL: String) : FilmActivityEvents()
    data class ShowSpecieEvent(val specieURL: String) : FilmActivityEvents()
    data class ShowPlanetEvent(val planetURL: String) : FilmActivityEvents()
    data class ShowVehicleEvent(val vehicleURL: String) : FilmActivityEvents()
    data class ShowStarShipEvent(val starShipURL: String) : FilmActivityEvents()

    data class FetchPersonEvent(val personURL: String) : FilmActivityEvents()
    data class FetchSpecieEvent(val specieURL: String) : FilmActivityEvents()
    data class FetchPlanetEvent(val planetURL: String) : FilmActivityEvents()
    data class FetchVehicleEvent(val vehicleURL: String) : FilmActivityEvents()
    data class FetchStarShipEvent(val starShipURL: String) : FilmActivityEvents()

    data class MovieFetchedEvent(val movies: FilmModel) : FilmActivityEvents()
    data class PersonFetchedEvent(val people: PersonModel) : FilmActivityEvents()
    data class SpecieFetchedEvent(val species: SpecieModel) : FilmActivityEvents()
    data class PlanetFetchedEvent(val planets: PlanetModel) : FilmActivityEvents()
    data class VehicleFetchedEvent(val vehicles: VehicleModel) : FilmActivityEvents()
    data class StarShipFetchedEvent(val starships: StarshipModel) : FilmActivityEvents()
}

