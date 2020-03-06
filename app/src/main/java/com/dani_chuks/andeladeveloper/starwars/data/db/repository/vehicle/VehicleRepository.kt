package com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    suspend fun getVehiclesByPredicate(predicate: DaoPredicate): List<Vehicle>

    suspend fun all(): List<Vehicle>

    suspend fun allAlphabetically(): List<Vehicle>

    suspend fun getItemsLimitedToSize(size: Int): List<Vehicle>

    suspend fun getItemByUrl(stringUrl: String): Vehicle?

    suspend fun insertItem(vehicle: Vehicle)

    suspend fun insertItemList(vehicles: List<Vehicle>)

    suspend fun fetchAndSync(page: Int) : Result<EntityList<Vehicle>>

    fun getVehiclesByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Vehicle>?>

    fun allAsFlow(): Flow<List<Vehicle>?>

    fun allAlphabeticallyAsFlow(): Flow<List<Vehicle>?>

    fun getVehiclesBySizeAsFlow(size: Int): Flow<List<Vehicle>?>

    fun getVehicleByNameAsFlow(name: String): Flow<Vehicle?>

    fun getVehicleByURLAsFlow(url: String): Flow<Vehicle?>
}
