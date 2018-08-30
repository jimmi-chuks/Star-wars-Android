package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface VehicleDao {
    @Query("SELECT * FROM vehicle")
    Flowable<List<Vehicle>> getAll();

    @Query("SELECT * FROM vehicle LIMIT 7")
    Flowable<List<Vehicle>> getFirstSeven();

    @Query("SELECT * FROM vehicle ORDER BY name ASC" )
    Flowable<List<Vehicle>> getAllAlphabetically();

    @Query("SELECT * FROM vehicle WHERE name = :starshipName")
    Flowable <Vehicle> getPeopleByName(String starshipName);

    @Query("SELECT * FROM vehicle WHERE url = :vehicleUrl")
    Flowable<Vehicle> getVehicleByURL(String vehicleUrl);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVehicleList(List<Vehicle> vehicleList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVehicle(Vehicle vehicle);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateVehicle(Vehicle vehicle);
}
