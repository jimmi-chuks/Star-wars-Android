package com.dani_chuks.andeladeveloper.starwars.data.db.remote


import com.dani_chuks.andeladeveloper.starwars.data.models.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @get:GET("/api/films")
    val allFilms: Observable<FilmList>

    @GET("/api/people/{id}")
    fun getPersonById(@Path("id") personId: Int): Observable<Person>

    @GET("/api/people/")
    fun getPeople(@Query("page") page: Int): Observable<People>

    @GET("/api/films/{id}")
    fun getFilmById(@Path("id") pathId: Int): Observable<Film>

    @GET("/api/starships/{id}")
    fun getStarshipById(@Path("id") starshipId: Int): Observable<Starship>

    @GET("/api/starships/")
    fun getStarshipList(@Query("page") page: Int): Observable<StarshipList>

    @GET("/api/vehicles/{id}")
    fun getVehicleById(@Path("id") starshipId: Int): Observable<Vehicle>

    @GET("/api/vehicles/")
    fun getVehicleList(@Query("page") page: Int): Observable<VehicleList>

    @GET("/api/species/{id}")
    fun getSpecieById(@Path("id") starshipId: Int): Observable<Specie>

    @GET("/api/species/")
    fun getSpecieList(@Query("page") page: Int): Observable<SpecieList>

    @GET("/api/planets/{id}")
    fun getPlanetById(@Path("id") starshipId: Int): Observable<Planet>

    @GET("/api/planets/")
    fun getPlanetList(@Query("page") page: Int): Observable<PlanetList>

}
