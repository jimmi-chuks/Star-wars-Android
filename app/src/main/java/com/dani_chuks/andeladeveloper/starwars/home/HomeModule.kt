package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie.SpecieRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship.StarshipRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle.VehicleRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.film.FilmRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.people.PeopleRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet.PlanetRepository
import com.dani_chuks.andeladeveloper.starwars.di.IDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class HomeModule {

    @Provides
    @Reusable
    fun providesViewModelInteractor(
            filmRepository: FilmRepository,
            planetRepository: PlanetRepository,
            specieRepository: SpecieRepository,
            starshipRepository: StarshipRepository,
            vehicleRepository: VehicleRepository,
            personRepository: PeopleRepository): HomeViewModelInteractor {
        return HomeViewModelInteractor(filmRepository, planetRepository, specieRepository, starshipRepository, vehicleRepository, personRepository)
    }

    @Provides
    @Reusable
    fun providesHomeViewMOdelFactory(viewModelInteractor: HomeViewModelInteractor, iDispatchersProvider: IDispatcherProvider): HomeViewModelFactory {
        return HomeViewModelFactory(viewModelInteractor, iDispatchersProvider)
    }


}
