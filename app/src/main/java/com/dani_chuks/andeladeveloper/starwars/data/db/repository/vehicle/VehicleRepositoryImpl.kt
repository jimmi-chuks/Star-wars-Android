package com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllAlphabetically
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllBySize
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepositoryImpl @Inject
constructor(private val remoteDataSource: VehicleRemoteDataSource,
            appDatabase: AppDatabase,
            private val preferenceManager: SharedPreferenceManager): VehicleRepository {

    private val vehicleDao = appDatabase.vehicleDao()

    override suspend fun getVehiclesByPredicate(predicate: DaoPredicate): List<Vehicle> {
        return when (predicate) {
            is GetAllAlphabetically -> vehicleDao.allAlphabetically()
            is GetAllBySize -> vehicleDao.getItemBySize(predicate.size)
            else -> vehicleDao.all()
        }
    }

    override suspend fun all(): List<Vehicle> {
        return vehicleDao.all()
    }

    override suspend fun allAlphabetically(): List<Vehicle> {
        return vehicleDao.allAlphabetically()
    }

    override suspend fun getItemsLimitedToSize(size: Int): List<Vehicle> {
        return vehicleDao.getItemBySize(size)
    }

    override suspend fun getItemByUrl(stringUrl: String): Vehicle? = coroutineScope {
        val vehicle = vehicleDao.getVehicleByURL(stringUrl)
        if (vehicle == null) {
            val vehicleId = DbUtils.getLastPathFromUrl(stringUrl)
            val vehicleFromRemote = remoteDataSource.getVehicleById(vehicleId)
            if (vehicleFromRemote is Result.Success) {
                vehicleFromRemote.data.let { vehicleDao.insert(it) }
            }
        }
        vehicle
    }

    override suspend fun insertItem(vehicle: Vehicle) = coroutineScope {
        vehicleDao.insert(vehicle)
    }

    override suspend fun insertItemList(vehicles: List<Vehicle>) = coroutineScope {
        vehicleDao.insertList(vehicles)
    }

    override suspend fun fetchAndSync(page: Int)= coroutineScope {
        val allVehicles = remoteDataSource.getVehicleFromPage(page)
        println("fetch and sync Vehicles from page $page ==> $allVehicles")
        if (allVehicles is Result.Success) {
            allVehicles.data.list?.let {
                vehicleDao.insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, 2)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME)
            }

        }
    }

    override fun getVehiclesByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Vehicle>?> {
        return when (predicate) {
            is GetAllAlphabetically -> vehicleDao.allAlphabeticallyAsFlow()
            is GetAllBySize -> vehicleDao.getItemBySizeAsFlow(predicate.size)
            else -> vehicleDao.allAsFlow()
        }
    }

    override fun allAsFlow(): Flow<List<Vehicle>?> = vehicleDao.allAsFlow()

    override fun allAlphabeticallyAsFlow(): Flow<List<Vehicle>?> = vehicleDao.allAlphabeticallyAsFlow()

    override fun getVehiclesBySizeAsFlow(size: Int): Flow<List<Vehicle>?> = vehicleDao.getItemBySizeAsFlow(size)

    override fun getVehicleByNameAsFlow(name: String): Flow<Vehicle?> = vehicleDao.getVehicleByNameAsFlow(name)

    override fun getVehicleByURLAsFlow(url: String): Flow<Vehicle?> = vehicleDao.getVehicleByURLAsFlow(url)
}