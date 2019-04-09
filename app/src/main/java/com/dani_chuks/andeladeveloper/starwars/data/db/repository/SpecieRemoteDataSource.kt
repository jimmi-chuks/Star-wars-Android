package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.SpecieList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import java.io.IOException
import javax.inject.Inject


class SpecieRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getSpeciesFromPage(page: Int) = safeApiCall(
            call = { requestSpecies(page) },
            errorMessage = "Error getting Species In Page"
    )

    suspend fun getPersonById(id: Int) = safeApiCall(
            call = { requestPersonById(id)},
            errorMessage = "Error getting person by Id"
    )

    private suspend fun requestSpecies(page: Int): Result<SpecieList> {
        val response = service.getSpecieList(page).await()
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error Fetching Species ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestPersonById(personId: Int): Result<Specie> {
        val specieResponse = service.getSpecieById(personId).await()
        if (specieResponse.isSuccessful) {
            val specieBody = specieResponse.body()
            if (specieBody != null) return Result.Success(specieBody)
        }
        return Result.Error(
                IOException("Error getting person for Id $personId ${specieResponse.code()} ${specieResponse.message()}")
        )
    }
}
