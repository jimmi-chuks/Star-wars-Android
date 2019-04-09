package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModel.Companion.firstPage
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class VehicleRepository @Inject
constructor(private val remoteDataSource: VehicleRemoteDataSource,
            private val appDatabase: AppDatabase,
            private val preferenceManager: SharedPreferenceManager) {

    suspend fun all(): List<Vehicle> {
            return appDatabase.vehicleDao().all()
        }

    suspend fun allAlphabetically(): List<Vehicle> {
            return appDatabase.vehicleDao().allAlphabetically()
        }

    suspend fun getItemsLimitedToSize(size: Int): List<Vehicle> {
        return appDatabase.vehicleDao().getItemBySize(size)
    }

    suspend fun getItemByUrl(stringUrl: String): Vehicle = coroutineScope {
        val vehicle = async { appDatabase.vehicleDao().getVehicleByURL(stringUrl)}.await()
        if(vehicle == null){
            val vehicleId = DbUtils.getLastPathFromUrl(stringUrl)
            val vehicleFromRemote = remoteDataSource.getVehicleById(vehicleId)
            if(vehicleFromRemote is Result.Success) {
                vehicleFromRemote.data.let { appDatabase.vehicleDao().insert(it) }
            }
        }
        vehicle
    }

    suspend fun insertItem(vehicle: Vehicle) = coroutineScope{
        appDatabase.vehicleDao().insert(vehicle)
    }

    suspend fun insertItemList(vehicles: List<Vehicle>) = coroutineScope{
        appDatabase.vehicleDao().insertList(vehicles)
    }

    suspend fun fetchAndSync(page: Int)= coroutineScope {
        val allVehicles = remoteDataSource.getVehicleFromPage(page)
        println("fetch and sync Vehicles from page $page ==> $allVehicles")
        if (allVehicles is Result.Success) {
            allVehicles.data.vehicle?.let {
                appDatabase.vehicleDao().insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, firstPage + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME)
            }

        }
        allVehicles
    }
}
