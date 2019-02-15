package com.dani_chuks.andeladeveloper.starwars.home

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
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
            personRepository: PersonRepository): HomeViewModelInteractor {
        return HomeViewModelInteractor(filmRepository, planetRepository, specieRepository, starshipRepository, vehicleRepository, personRepository)
    }

    @Provides
    @Reusable
    fun providesHomeViewMOdelFactory(viewModelInteractor: HomeViewModelInteractor, iSchedulerProvider: ISchedulerProvider): HomeViewModelFactory {
        return HomeViewModelFactory(viewModelInteractor, iSchedulerProvider)
    }


}
