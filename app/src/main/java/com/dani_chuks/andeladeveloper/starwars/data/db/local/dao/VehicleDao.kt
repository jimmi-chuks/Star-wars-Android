package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import io.reactivex.Flowable

@Dao
interface VehicleDao {
    @get:Query("SELECT * FROM vehicle")
    val all: Flowable<List<Vehicle>>

    @get:Query("SELECT * FROM vehicle ORDER BY name ASC")
    val allAlphabetically: Flowable<List<Vehicle>>

    @Query("SELECT * FROM vehicle LIMIT :size")
    fun getItemBySize(size: Int): Flowable<List<Vehicle>>

    @Query("SELECT * FROM vehicle WHERE name = :starshipName")
    fun getPeopleByName(starshipName: String): Flowable<Vehicle>

    @Query("SELECT * FROM vehicle WHERE url = :vehicleUrl")
    fun getVehicleByURL(vehicleUrl: String): Flowable<Vehicle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVehicleList(vehicleList: List<Vehicle>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVehicle(vehicle: Vehicle)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateVehicle(vehicle: Vehicle)
}
