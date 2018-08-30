package com.dani_chuks.andeladeveloper.starwars.film;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

public interface FilmView {
    void showFilms(List<Film> films);
    void showVehicles(List<Vehicle> vehicles);
    void showSpecies(List<Specie> species);
    void showStarships(List<Starship> starships);
    void hideStarShipGroup();
    void hidefilmGroup();
    void hideSpeciesGroup();
    void hideStarshipGroup();
}
