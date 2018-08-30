package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PlanetDao {
    @Query("SELECT * FROM planet")
    Flowable<List<Planet>> getAll();

    @Query("SELECT * FROM planet LIMIT 7")
    Flowable<List<Planet>> getFirstSeven();

    @Query("SELECT * FROM planet ORDER BY name ASC" )
    Flowable<List<Planet>> getAllAlphabetically();

    @Query("SELECT * FROM planet WHERE name = :name")
    Flowable <List<Planet>> getplanetsByName(String name);

    @Query("SELECT * FROM planet WHERE url = :planetUrl")
    Flowable<Planet> getplanetByURL(String planetUrl);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateplanets(List<Planet> planetList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertplanet(List<Planet> planetList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertplanet(Planet planet);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateplanet(Planet planet);
}
