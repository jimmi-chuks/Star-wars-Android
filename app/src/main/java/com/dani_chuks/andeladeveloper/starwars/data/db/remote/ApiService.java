package com.dani_chuks.andeladeveloper.starwars.data.db.remote;


import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.FilmList;
import com.dani_chuks.andeladeveloper.starwars.data.models.People;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.PlanetList;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.SpecieList;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.StarshipList;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;
import com.dani_chuks.andeladeveloper.starwars.data.models.VehicleList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/api/people/{id}")
    Observable<Person> getPersonById(@Path("id") int personId);

    @GET("/api/people/")
    Observable<People> getPeople(@Query("page") int page);

    @GET("/api/films/{id}")
    Observable<Film> getFilmById(@Path("id") int pathId);

    @GET("/api/films")
    Observable<FilmList> getAllFilms();

    @GET("/api/starships/{id}")
    Observable<Starship> getStarshipById(@Path("id") int starshipId);

    @GET("/api/starships/")
    Observable<StarshipList> getStarshipList(@Query("page") int page);

    @GET("/api/vehicles/{id}")
    Observable<Vehicle> getVehicleById(@Path("id") int starshipId);

    @GET("/api/vehicles/")
    Observable<VehicleList> getVehicleList(@Query("page") int page);

    @GET("/api/species/{id}")
    Observable<Specie> getSpecieById(@Path("id") int starshipId);

    @GET("/api/species/")
    Observable<SpecieList> getSpecieList(@Query("page") int page);

    @GET("/api/planets/{id}")
    Observable<Planet> getPlanetById(@Path("id") int starshipId);

    @GET("/api/planets/")
    Observable<PlanetList> getPlanetList(@Query("page") int page);

}
