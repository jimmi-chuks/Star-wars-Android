package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StarshipDao {
    @Query("SELECT * FROM starship")
    Flowable<List<Starship>> getAll();

    @Query("SELECT * FROM starship LIMIT 7")
    Flowable<List<Starship>> getFirstSeven();

    @Query("SELECT * FROM starship ORDER BY name ASC" )
    Flowable<List<Starship>> getAllAlphabetically();

    @Query("SELECT * FROM starship WHERE name = :starshipName")
    Flowable <Starship> getPeopleByName(String starshipName);

    @Query("SELECT * FROM starship WHERE url = :starshipUrl")
    Flowable<Starship> getStarshipByURL(String starshipUrl);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStarshipList(List<Starship> starships);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStarship(Starship starship);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateS(Vehicle vehicle);
}
