package com.dani_chuks.andeladeveloper.starwars.data.db;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.LocalDataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public class Repository implements DataSource {

    @NonNull
    final LocalDataSource localDataSource;
    @NonNull
    final ApiService apiService;
    @NonNull final ISchedulerProvider schedulerProvider;

    public Repository(@NonNull final LocalDataSource localDataSource, @NonNull final ApiService apiService,
                      @NonNull final ISchedulerProvider schedulerProvider) {
        this.localDataSource = localDataSource;
        this.apiService = apiService;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Flowable<List<Specie>> getAllSpecies() {
        return null;
    }

    @Override
    public Flowable<List<Specie>> getFirstSevenSpecies() {
        return null;
    }

    @Override
    public Flowable<List<Specie>> getAllSpeciesAlphabetically() {
        return null;
    }

    @Override
    public Flowable<Specie> getSpecieByUrl(final String specieUrl) {
        return null;
    }

    @Override
    public void insertSpecie(final List<Specie> specieList) {

    }

    @Override
    public void insertSpecie(final Specie specie) {

    }

    @Override
    public Flowable<List<Film>> getAllFilms() {
        return null;
    }

    @Override
    public Flowable<List<Film>> getFirstSevenFilms() {
        return null;
    }

    @Override
    public Flowable<List<Film>> getAllFilmsAlphabetically() {
        return null;
    }

    @Override
    public Flowable<Film> getFilmByUrl(final String filmUrl) {
//        int url = DbUtils.getLastPathFromUrl(filmUrl);
        return null;
    }

    @Override
    public void insertFilms(final List<Film> films) {

    }

    @Override
    public void insertFilm(final Film film) {

    }

    @Override
    public Flowable<List<Person>> getAllPersons() {
        return null;
    }

    @Override
    public Flowable<List<Person>> getFirstSevenPeople() {
        return null;
    }

    @Override
    public Flowable<List<Person>> getAllPersonsAlphabetically() {
        return null;
    }

    @Override
    public Flowable<List<Person>> getPeopleByGender(final String gender) {
        return null;
    }

    @Override
    public Flowable<Person> getPersonByURL(final String personUrl) {
        return null;
    }

    @Override
    public void insertPeople(final List<Person> people) {

    }

    @Override
    public void insertPerson(final Person person) {

    }

    @Override
    public Flowable<List<Planet>> getAllPlanets() {
        return null;
    }

    @Override
    public Flowable<List<Planet>> getFirstSevenlanets() {
        return null;
    }

    @Override
    public Flowable<List<Planet>> getAllPlanetsAlphabetically() {
        return null;
    }

    @Override
    public Flowable<Planet> getPlanetByURL(final String planetUrl) {
        return null;
    }

    @Override
    public long[] insertPlanet(final List<Planet> planetList) {
        return new long[0];
    }

    @Override
    public void insertplanet(final Planet planet) {

    }

    @Override
    public Flowable<List<Starship>> getAllStarships() {
        return null;
    }

    @Override
    public Flowable<List<Starship>> getFirstSevenStarships() {
        return null;
    }

    @Override
    public Flowable<List<Starship>> getAllStarshipsAlphabetically() {
        return null;
    }

    @Override
    public Flowable<Starship> getStarshipByURL(final String starshipUrl) {
        return null;
    }

    @Override
    public void insertStarshipList(final List<Starship> starships) {

    }

    @Override
    public void insertStarship(final Starship starship) {

    }

    @Override
    public void updateS(final Vehicle vehicle) {

    }

    @Override
    public Flowable<List<Vehicle>> getAllVehicles() {
        return null;
    }

    @Override
    public Flowable<List<Vehicle>> getFirstSevenVehicles() {
        return null;
    }

    @Override
    public Flowable<List<Vehicle>> getAllVehiclesAlphabetically() {
        return null;
    }

    @Override
    public Flowable<Vehicle> getVehicleByURL(final String vehicleUrl) {
        return null;
    }

    @Override
    public void insertVehicleList(final List<Vehicle> vehicleList) {

    }

    @Override
    public void insertVehicle(final Vehicle vehicle) {

    }
}
