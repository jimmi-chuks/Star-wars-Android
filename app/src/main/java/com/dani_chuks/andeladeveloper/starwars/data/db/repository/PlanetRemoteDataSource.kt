package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.PlanetList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import java.io.IOException
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(private val service: ApiService){

    suspend fun getAllPlanets(page: Int) = safeApiCall(
            call = { requestAllPlanets(page) },
            errorMessage = "Error getting all Planets"
    )

    suspend fun getPlanetById(id: Int) = safeApiCall(
            call = { requestPlanetById(id) },
            errorMessage = "Error getting Planet with ID"
    )


    private suspend fun requestAllPlanets(page: Int): Result<PlanetList> {
        val response = service.getPlanetList(page).await()
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error getting all planets ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestPlanetById(planetId: Int): Result<Planet> {
        val planetResponse = service.getPlanetById(planetId).await()
        if (planetResponse.isSuccessful) {
            val planetBody = planetResponse.body()
            if (planetBody != null) return Result.Success(planetBody)
        }
        return Result.Error(
                IOException("Error getting planet for Id $planetId ${planetResponse.code()} ${planetResponse.message()}")
        )
    }
}