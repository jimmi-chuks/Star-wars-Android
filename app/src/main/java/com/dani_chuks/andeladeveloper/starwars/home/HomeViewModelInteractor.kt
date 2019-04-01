package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.starwars.dagger.Result
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
import com.dani_chuks.andeladeveloper.starwars.data.models.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class HomeViewModelInteractor @Inject constructor(
        val filmRepository: FilmRepository,
        val planetRepository: PlanetRepository,
        val specieRepository: SpecieRepository,
        val starshipRepository: StarshipRepository,
        val vehicleRepository: VehicleRepository,
        val personRepository: PersonRepository) {

    internal suspend fun loadPeople(): List<Person> {
        return personRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadPlanets(): List<Planet> {
        return planetRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadVehicles(): List<Vehicle> {
        return vehicleRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadStarships(): List<Starship> {
        return starshipRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadSpecies(): List<Specie> {
        return specieRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadFilms(): List<Film> {
        return filmRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal suspend fun loadFilmsRemote(): Result<FilmList> = coroutineScope{
        filmRepository.fetchAndSync()
    }

    internal suspend fun loadPeopleRemote(page: Int): Result<People> = coroutineScope {
        personRepository.fetchAndSync(page)
    }

    internal suspend fun loadPlanetRemote(page: Int): Result<PlanetList> = coroutineScope {
        planetRepository.fetchAndSync(page)
    }

    internal suspend fun loadVehicleRemote(page: Int): Result<VehicleList> = coroutineScope {
        vehicleRepository.fetchAndSync(page)
    }

    internal suspend fun loadSpeciesRemote(page: Int): Result<SpecieList> = coroutineScope {
        specieRepository.fetchAndSync(page)
    }

    internal suspend fun loadStarshipsRemote(page: Int): Result<StarshipList> = coroutineScope {
        starshipRepository.fetchAndSync(page)
    }

    companion object {
        val ITEM_LIMIT = 10
    }

}
