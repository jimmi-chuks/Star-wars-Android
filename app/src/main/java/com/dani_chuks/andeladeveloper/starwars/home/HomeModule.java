package com.dani_chuks.andeladeveloper.starwars.home;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.FilmRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PersonRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PlanetRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.SpecieRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.StarshipRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.VehicleRepository;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class HomeModule {

    @Provides
    @Reusable
    HomeViewModelInteractor providesViewModelInteractor(
            @NonNull final FilmRepository filmRepository,
            @NonNull final PlanetRepository planetRepository,
            @NonNull final SpecieRepository specieRepository,
            @NonNull final StarshipRepository starshipRepository,
            @NonNull final VehicleRepository vehicleRepository,
            @NonNull final PersonRepository personRepository){
        return  new HomeViewModelInteractor(filmRepository, planetRepository, specieRepository, starshipRepository, vehicleRepository, personRepository);
    }

    @Provides
    @Reusable
    HomeViewModelFactory providesHomeViewMOdelFactory(@NonNull final HomeViewModelInteractor viewModelInteractor, @NonNull final ISchedulerProvider iSchedulerProvider){
        return  new HomeViewModelFactory(viewModelInteractor, iSchedulerProvider);
    }



}
