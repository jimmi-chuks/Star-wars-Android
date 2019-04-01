package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle

@Dao
abstract class VehicleDao: BaseDao<Vehicle> {
    @Query("SELECT * FROM vehicle")
    abstract suspend fun all(): List<Vehicle>

    @Query("SELECT * FROM vehicle ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Vehicle>

    @Query("SELECT * FROM vehicle LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<Vehicle>

    @Query("SELECT * FROM vehicle WHERE name = :starshipName")
    abstract suspend fun getPeopleByName(starshipName: String): Vehicle

    @Query("SELECT * FROM vehicle WHERE url = :vehicleUrl")
    abstract suspend fun getVehicleByURL(vehicleUrl: String): Vehicle
}
