package com.dani_chuks.andeladeveloper.starwars.home;


import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.FilmRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PersonRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PlanetRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.SpecieRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.StarshipRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.VehicleRepository;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public class HomeViewModelInteractor {
    @NonNull private final FilmRepository filmRepository;
    @NonNull private final PlanetRepository planetRepository;
    @NonNull private final SpecieRepository specieRepository;
    @NonNull private final StarshipRepository starshipRepository;
    @NonNull private final VehicleRepository vehicleRepository;
    @NonNull private final PersonRepository personRepository;
    private final static int ITEM_LIMIT = 10;

    public HomeViewModelInteractor(
            @NonNull final FilmRepository filmRepository,
            @NonNull final PlanetRepository planetRepository,
            @NonNull final SpecieRepository specieRepository,
            @NonNull final StarshipRepository starshipRepository,
            @NonNull final VehicleRepository vehicleRepository,
            @NonNull final PersonRepository personRepository) {
        this.filmRepository = filmRepository;
        this.planetRepository = planetRepository;
        this.specieRepository = specieRepository;
        this.starshipRepository = starshipRepository;
        this.vehicleRepository = vehicleRepository;
        this.personRepository = personRepository;
    }

    Flowable<List<Person>> loadPeople(){
       return personRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

    Flowable<List<Planet>> loadPlanets(){
        return planetRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

    Flowable<List<Vehicle>> loadVehicles(){
        return vehicleRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

    Flowable<List<Starship>> loadStarships(){
        return starshipRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

    Flowable<List<Specie>> loadSpecies(){
        return specieRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

    Flowable<List<Film>> loadFilms(){
        return filmRepository.getItemsLimitedToSize(ITEM_LIMIT);
    }

}
