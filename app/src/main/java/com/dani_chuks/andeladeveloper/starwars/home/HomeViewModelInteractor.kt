package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.presentation_models.MainModels
import com.dani_chuks.andeladeveloper.presentation_models.mappers.Mapper
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
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeViewModelInteractor @Inject constructor(
        val filmRepository: FilmRepository,
        val planetRepository: PlanetRepository,
        val specieRepository: SpecieRepository,
        val starshipRepository: StarshipRepository,
        val vehicleRepository: VehicleRepository,
        val personRepository: PeopleRepository) {

    fun loadPeople(): Flow<List<Person>?> = personRepository.getPeopleByPredicateAsFlow(GetAllBySize(ITEM_LIMIT))

    fun loadPlanets(): Flow<List<Planet>?> = planetRepository.allAsFlow()

    fun loadVehicles(): Flow<List<Vehicle>?> = vehicleRepository.getVehiclesBySizeAsFlow(ITEM_LIMIT)

    fun loadStarships(): Flow<List<MainModels.StarshipModel>?> =
            starshipRepository.getStarShipsLimitedToSizeAsFlow(ITEM_LIMIT)
                    .map { it?.let{ Mapper.mapStarships(it) } }

    fun loadSpecies(): Flow<List<Specie>?> = specieRepository.getItemBySizeAsFlow(ITEM_LIMIT)

    fun loadFilms(): Flow<List<Film>?> = filmRepository.getFilmsByPredicateAsFlow(GetAllBySize(ITEM_LIMIT))

    suspend fun loadFilmsRemote() = filmRepository.fetchAndSync()

    suspend fun loadPeopleRemote(page: Int) = personRepository.fetchAndSync(page)

    suspend fun loadPlanetRemote(page: Int): Result<Boolean> = planetRepository.fetchAndSync(page)

    suspend fun loadVehicleRemote(page: Int) = vehicleRepository.fetchAndSync(page)

    suspend fun loadSpeciesRemote(page: Int) = specieRepository.fetchAndSync(page)

    suspend fun loadStarshipsRemote(page: Int) = starshipRepository.fetchAndSync(page)

    companion object {
        val ITEM_LIMIT = 10
    }

}
