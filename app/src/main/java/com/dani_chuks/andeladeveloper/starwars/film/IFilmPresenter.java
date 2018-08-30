package com.dani_chuks.andeladeveloper.starwars.film;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

public interface IFilmPresenter {
    void initView(String filmId, FilmView view);
    void onSpeciesFetched(List<Specie> specieList);
    void onStarshipFetched(List<Starship> starshipList);
    void onVehiclesFetched(List<Vehicle> vehicleList);
    void onPlanetsFetched(List<Planet> planets);
}
