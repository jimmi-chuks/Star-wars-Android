package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.VehicleList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import java.io.IOException
import javax.inject.Inject

class VehicleRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getVehicleFromPage(page: Int) = safeApiCall(
            call = { requestVehicle(page) },
            errorMessage = "Error getting Vehicle In Page"
    )

    suspend fun getVehicleById(id: Int) = safeApiCall(
            call = { requestVehicleById(id)},
            errorMessage = "Error getting vehicle by Id"
    )

    private suspend fun requestVehicle(page: Int): Result<VehicleList> {
        val response = service.getVehicleList(page).await()
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error Fetching Vehicle ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestVehicleById(vehicleId: Int): Result<Vehicle> {
        val vehicleResponse = service.getVehicleById(vehicleId).await()
        if (vehicleResponse.isSuccessful) {
            val vehicleBody = vehicleResponse.body()
            if (vehicleBody != null) return Result.Success(vehicleBody)
        }
        return Result.Error(
                IOException("Error getting vehicle for Id $vehicleId ${vehicleResponse.code()} ${vehicleResponse.message()}")
        )
    }
}
