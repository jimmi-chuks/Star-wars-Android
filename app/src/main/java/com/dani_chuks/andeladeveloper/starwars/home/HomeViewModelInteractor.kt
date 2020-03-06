package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllBySize
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.film.FilmRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.people.PeopleRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet.PlanetRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie.SpecieRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship.StarshipRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle.VehicleRepository
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeViewModelInteractor @Inject constructor(
        val filmRepository: FilmRepository,
        val planetRepository: PlanetRepository,
        val specieRepository: SpecieRepository,
        val starshipRepository: StarshipRepository,
        val vehicleRepository: VehicleRepository,
        val personRepository: PeopleRepository) {

    internal fun loadPeople(): Flow<List<Person>?> {
        return personRepository.getPeopleByPredicateAsFlow(GetAllBySize(ITEM_LIMIT))
    }

    internal fun loadPlanets(): Flow<List<Planet>?> {
        return planetRepository.allAsFlow()
    }

    internal fun loadVehicles(): Flow<List<Vehicle>?> {
        return vehicleRepository.getVehiclesBySizeAsFlow(ITEM_LIMIT)
    }

    internal fun loadStarships(): Flow<List<StarShip>?> {
        return starshipRepository.getStarShipsLimitedToSizeAsFlow(ITEM_LIMIT)
    }

    internal fun loadSpecies(): Flow<List<Specie>?> {
        return specieRepository.getItemBySizeAsFlow(ITEM_LIMIT)
    }

    fun loadFilms(): Flow<List<Film>?> {
        return filmRepository.getFilmsByPredicateAsFlow(GetAllBySize(ITEM_LIMIT))
    }

    internal suspend fun loadFilmsRemote(): Result<EntityList<Film>> = coroutineScope{
        filmRepository.fetchAndSync()
    }

    internal suspend fun loadPeopleRemote(page: Int): Result<EntityList<Person>> = coroutineScope {
        personRepository.fetchAndSync(page)
    }

    internal suspend fun loadPlanetRemote(page: Int): Result<EntityList<Planet>> = coroutineScope {
        planetRepository.fetchAndSync(page)
    }

    internal suspend fun loadVehicleRemote(page: Int): Result<EntityList<Vehicle>> = coroutineScope {
        vehicleRepository.fetchAndSync(page)
    }

    internal suspend fun loadSpeciesRemote(page: Int): Result<EntityList<Specie>> = coroutineScope {
        specieRepository.fetchAndSync(page)
    }

    internal suspend fun loadStarshipsRemote(page: Int): Result<EntityList<StarShip>> = coroutineScope {
        starshipRepository.fetchAndSync(page)
    }

    companion object {
        val ITEM_LIMIT = 10
    }

}
