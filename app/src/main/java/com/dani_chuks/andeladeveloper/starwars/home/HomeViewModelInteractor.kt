package com.dani_chuks.andeladeveloper.starwars.home


import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import io.reactivex.Flowable
import javax.inject.Inject

class HomeViewModelInteractor @Inject constructor(
        val filmRepository: FilmRepository,
        private val planetRepository: PlanetRepository,
        private val specieRepository: SpecieRepository,
        private val starshipRepository: StarshipRepository,
        private val vehicleRepository: VehicleRepository,
        private val personRepository: PersonRepository) {

    internal fun loadPeople(): Flowable<List<Person>> {
        return personRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal fun loadPlanets(): Flowable<List<Planet>> {
        return planetRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal fun loadVehicles(): Flowable<List<Vehicle>> {
        return vehicleRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal fun loadStarships(): Flowable<List<Starship>> {
        return starshipRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal fun loadSpecies(): Flowable<List<Specie>> {
        return specieRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    internal fun loadFilms(): Flowable<List<Film>> {
        return filmRepository.getItemsLimitedToSize(ITEM_LIMIT)
    }

    companion object {
        private val ITEM_LIMIT = 10
    }

}
