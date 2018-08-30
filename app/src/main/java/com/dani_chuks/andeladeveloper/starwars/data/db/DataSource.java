package com.dani_chuks.andeladeveloper.starwars.data.db;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSource {

    Flowable<List<Specie>> getAllSpecies();

    Flowable<List<Specie>> getFirstSevenSpecies();

    Flowable<List<Specie>> getAllSpeciesAlphabetically();

    Flowable<Specie> getSpecieByUrl(String specieUrl);

    void insertSpecie(List<Specie> specieList);

    void insertSpecie(Specie specie);

    Flowable<List<Film>> getAllFilms();

    Flowable<List<Film>> getFirstSevenFilms();

    Flowable<List<Film>> getAllFilmsAlphabetically();

    Flowable<Film> getFilmByUrl(String filmUrl);

    void insertFilms(List<Film> films);

    void insertFilm(Film film);

    Flowable<List<Person>> getAllPersons();

    Flowable<List<Person>> getFirstSevenPeople();

    Flowable<List<Person>> getAllPersonsAlphabetically();

    Flowable<List<Person>> getPeopleByGender(String gender);

    Flowable<Person> getPersonByURL(String personUrl);

    void insertPeople(List<Person> people);

    void insertPerson(Person person);

    Flowable<List<Planet>> getAllPlanets();

    Flowable<List<Planet>> getFirstSevenlanets();

    Flowable<List<Planet>> getAllPlanetsAlphabetically();

    Flowable<Planet> getPlanetByURL(String planetUrl);

    long[] insertPlanet(List<Planet> planetList);

    void insertplanet(Planet planet);

    Flowable<List<Starship>> getAllStarships();

    Flowable<List<Starship>> getFirstSevenStarships();

    Flowable<List<Starship>> getAllStarshipsAlphabetically();

    Flowable<Starship> getStarshipByURL(String starshipUrl);

    void insertStarshipList(List<Starship> starships);

    void insertStarship(Starship starship);

    void updateS(Vehicle vehicle);

    Flowable<List<Vehicle>> getAllVehicles();

    Flowable<List<Vehicle>> getFirstSevenVehicles();

    Flowable<List<Vehicle>> getAllVehiclesAlphabetically();

    Flowable<Vehicle> getVehicleByURL(String vehicleUrl);

    void insertVehicleList(List<Vehicle> vehicleList);

    void insertVehicle(Vehicle vehicle);
}
