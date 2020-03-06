package com.dani_chuks.andeladeveloper.starwars.data.db.remote


import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("/api/films")
    suspend fun allFilms(): Response<EntityList<Film>>

    @GET("/api/people/{id}")
    suspend fun getPersonById(@Path("id") personId: Int): Response<Person>

    @GET("/api/people/")
    suspend fun getPeople(@Query("page") page: Int): Response<EntityList<Person>>

    @GET("/api/films/{id}")
    suspend fun getFilmById(@Path("id") pathId: Int): Response<Film>

    @GET("/api/starships/{id}")
    suspend fun getStarshipById(@Path("id") starshipId: Int): Response<StarShip>

    @GET("/api/starships/")
    suspend fun getStarshipList(@Query("page") page: Int): Response<EntityList<StarShip>>

    @GET("/api/vehicles/{id}")
    suspend fun getVehicleById(@Path("id") starshipId: Int): Response<Vehicle>

    @GET("/api/vehicles/")
    suspend fun getVehicleList(@Query("page") page: Int): Response<EntityList<Vehicle>>

    @GET("/api/species/{id}")
    suspend fun getSpecieById(@Path("id") starshipId: Int): Response<Specie>

    @GET("/api/species/")
    suspend fun getSpecieList(@Query("page") page: Int): Response<EntityList<Specie>>

    @GET("/api/planets/{id}")
    suspend fun getPlanetById(@Path("id") starshipId: Int): Response<Planet>

    @GET("/api/planets/")
    suspend fun getPlanetList(@Query("page") page: Int): Response<EntityList<Planet>>

}
