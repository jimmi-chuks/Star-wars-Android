package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public class LocalDataSource implements DataSource {
    @NonNull final AppDatabase appDatabase;

    public LocalDataSource(@NonNull final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Flowable<List<Specie>> getAllSpecies() {
        return appDatabase.specieDao().getAll();
    }

    @Override
    public Flowable<List<Specie>> getFirstSevenSpecies() {
        return appDatabase.specieDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Specie>> getAllSpeciesAlphabetically() {
        return appDatabase.specieDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Specie> getSpecieByUrl(final String specieUrl) {
        return appDatabase.specieDao().getSpecieByUrl(specieUrl);
    }

    @Override
    public void insertSpecie(final List<Specie> specieList) {
        appDatabase.specieDao().insertSpecie(specieList);
    }

    @Override
    public void insertSpecie(final Specie specie) {
        appDatabase.specieDao().insertSpecie(specie);
    }

    @Override
    public Flowable<List<Film>> getAllFilms() {
        return appDatabase.filmDao().getAll();
    }

    @Override
    public Flowable<List<Film>> getFirstSevenFilms() {
        return appDatabase.filmDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Film>> getAllFilmsAlphabetically() {
        return appDatabase.filmDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Film> getFilmByUrl(final String filmUrl) {
        return appDatabase.filmDao().getFilmByUrl(filmUrl);
    }

    @Override
    public void insertFilms(final List<Film> films) {
        appDatabase.filmDao().insertFilms(films);
    }

    @Override
    public void insertFilm(final Film film) {
        appDatabase.filmDao().insertFilm(film);
    }

    @Override
    public Flowable<List<Person>> getAllPersons() {
        return appDatabase.personDao().getAll();
    }

    @Override
    public Flowable<List<Person>> getFirstSevenPeople() {
        return appDatabase.personDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Person>> getAllPersonsAlphabetically() {
        return appDatabase.personDao().getAllAlphabetically();
    }

    @Override
    public Flowable<List<Person>> getPeopleByGender(final String gender) {
        return appDatabase.personDao().getPeopleByGender(gender);
    }

    @Override
    public Flowable<Person> getPersonByURL(final String personUrl) {
        return appDatabase.personDao().getPersonByURL(personUrl);
    }

    @Override
    public void insertPeople(final List<Person> people) {
        appDatabase.personDao().insertPeople(people);
    }

    @Override
    public void insertPerson(final Person person) {
        appDatabase.personDao().insertPerson(person);
    }

    @Override
    public Flowable<List<Planet>> getAllPlanets() {
        return appDatabase.planetDao().getAll();
    }

    @Override
    public Flowable<List<Planet>> getFirstSevenlanets() {
        return appDatabase.planetDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Planet>> getAllPlanetsAlphabetically() {
        return appDatabase.planetDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Planet> getPlanetByURL(final String planetUrl) {
        return appDatabase.planetDao().getplanetByURL(planetUrl);
    }

    @Override
    public long[] insertPlanet(final List<Planet> planetList) {
        return appDatabase.planetDao().insertplanet(planetList);
    }

    @Override
    public void insertplanet(final Planet planet) {
        appDatabase.planetDao().insertplanet(planet);
    }

    @Override
    public Flowable<List<Starship>> getAllStarships() {
        return appDatabase.starshipDao().getAll();
    }

    @Override
    public Flowable<List<Starship>> getFirstSevenStarships() {
        return appDatabase.starshipDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Starship>> getAllStarshipsAlphabetically() {
        return appDatabase.starshipDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Starship> getStarshipByURL(final String starshipUrl) {
        return appDatabase.starshipDao().getStarshipByURL(starshipUrl);
    }

    @Override
    public void insertStarshipList(final List<Starship> starships) {
        appDatabase.starshipDao().insertStarshipList(starships);
    }

    @Override
    public void insertStarship(final Starship starship) {
        appDatabase.starshipDao().insertStarship(starship);
    }

    @Override
    public void updateS(final Vehicle vehicle) {
        appDatabase.vehicleDao().updateVehicle(vehicle);
    }

    @Override
    public Flowable<List<Vehicle>> getAllVehicles() {
        return appDatabase.vehicleDao().getAll();
    }

    @Override
    public Flowable<List<Vehicle>> getFirstSevenVehicles() {
        return appDatabase.vehicleDao().getFirstSeven();
    }

    @Override
    public Flowable<List<Vehicle>> getAllVehiclesAlphabetically() {
        return appDatabase.vehicleDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Vehicle> getVehicleByURL(final String vehicleUrl) {
        return appDatabase.vehicleDao().getVehicleByURL(vehicleUrl);
    }

    @Override
    public void insertVehicleList(final List<Vehicle> vehicleList) {
        appDatabase.vehicleDao().insertVehicleList(vehicleList);
    }

    @Override
    public void insertVehicle(final Vehicle vehicle) {
        appDatabase.vehicleDao().insertVehicle(vehicle);
    }
}
