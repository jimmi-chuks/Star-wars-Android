package com.dani_chuks.andeladeveloper.starwars.home;

import android.content.Context;

public interface HomeView {
    void showAllFilms();
    void showAllCharacters();
    void showAllVehicles();
    void showAllSpecies();
    void showAllStarships();
    void showFilm(int filmId);
    void showCharacter(int characterId);
    void showVehicle(int vehicleId);
    void showSpecies(int specieId);
    void showStarship(int starshipId);
    void initializeView();
    void hideProgress(String resourceName);
    Context getViewContext();
}
