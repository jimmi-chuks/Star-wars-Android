package com.dani_chuks.andeladeveloper.starwars.data.db.remote


import com.dani_chuks.andeladeveloper.starwars.data.models.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @get:GET("/api/films")
    val allFilms: Deferred<Response<FilmList>>

    @GET("/api/people/{id}")
    fun getPersonById(@Path("id") personId: Int): Deferred<Response<Person>>

    @GET("/api/people/")
    fun getPeople(@Query("page") page: Int): Deferred<Response<People>>

    @GET("/api/films/{id}")
    fun getFilmById(@Path("id") pathId: Int): Deferred<Response<Film>>

    @GET("/api/starships/{id}")
    fun getStarshipById(@Path("id") starshipId: Int): Deferred<Response<Starship>>

    @GET("/api/starships/")
    fun getStarshipList(@Query("page") page: Int): Deferred<Response<StarshipList>>

    @GET("/api/vehicles/{id}")
    fun getVehicleById(@Path("id") starshipId: Int): Deferred<Response<Vehicle>>

    @GET("/api/vehicles/")
    fun getVehicleList(@Query("page") page: Int): Deferred<Response<VehicleList>>

    @GET("/api/species/{id}")
    fun getSpecieById(@Path("id") starshipId: Int): Deferred<Response<Specie>>

    @GET("/api/species/")
    fun getSpecieList(@Query("page") page: Int): Deferred<Response<SpecieList>>

    @GET("/api/planets/{id}")
    fun getPlanetById(@Path("id") starshipId: Int): Deferred<Response<Planet>>

    @GET("/api/planets/")
    fun getPlanetList(@Query("page") page: Int): Deferred<Response<PlanetList>>

}
