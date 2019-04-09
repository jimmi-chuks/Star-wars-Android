package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.StarshipList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship
import java.io.IOException
import javax.inject.Inject

class StarshipRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getStarshipFromPage(page: Int) = safeApiCall(
            call = { requestStarship(page) },
            errorMessage = "Error getting Starship In Page"
    )

    suspend fun getStarshipById(id: Int) = safeApiCall(
            call = { requestStarshipById(id)},
            errorMessage = "Error getting starship by Id"
    )

    private suspend fun requestStarship(page: Int): Result<StarshipList> {
        val response = service.getStarshipList(page).await()
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error Fetching Starship ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestStarshipById(starshipId: Int): Result<Starship> {
        val starshipResponse = service.getStarshipById(starshipId).await()
        if (starshipResponse.isSuccessful) {
            val starshipBody = starshipResponse.body()
            if (starshipBody != null) return Result.Success(starshipBody)
        }
        return Result.Error(
                IOException("Error getting starship for Id $starshipId ${starshipResponse.code()} ${starshipResponse.message()}")
        )
    }
}
