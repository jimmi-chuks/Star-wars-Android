package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
abstract class VehicleDao: BaseDao<Vehicle> {
    @Query("SELECT * FROM vehicle")
    abstract suspend fun all(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Vehicle>

    @Query("SELECT * FROM vehicle LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<Vehicle>

    @Query("SELECT * FROM vehicle WHERE name = :starshipName")
    abstract suspend fun getVehicleByName(starshipName: String): Vehicle

    @Query("SELECT * FROM vehicle WHERE url = :vehicleUrl")
    abstract suspend fun getVehicleByURL(vehicleUrl: String): Vehicle?

    @Query("SELECT * FROM vehicle")
    abstract fun allAsFlow(): Flow<List<Vehicle>?>

    @Query("SELECT * FROM vehicle ORDER BY name ASC")
    abstract fun allAlphabeticallyAsFlow(): Flow<List<Vehicle>?>

    @Query("SELECT * FROM vehicle LIMIT :size")
    abstract fun getItemBySizeAsFlow(size: Int): Flow<List<Vehicle>?>

    @Query("SELECT * FROM vehicle WHERE name = :name")
    abstract fun getVehicleByNameAsFlow(name: String): Flow<Vehicle?>

    @Query("SELECT * FROM vehicle WHERE url = :url")
    abstract fun getVehicleByURLAsFlow(url: String): Flow<Vehicle?>
}
