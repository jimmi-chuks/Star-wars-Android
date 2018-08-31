package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface SpecieDao {
    @Query("SELECT * FROM specie")
    Flowable<List<Specie>> getAll();

    @Query("SELECT * FROM specie LIMIT :size")
    Flowable<List<Specie>> getItemBySize(int size);

    @Query("SELECT * FROM specie ORDER BY name ASC" )
    Flowable<List<Specie>> getAllAlphabetically();

    @Query("SELECT * FROM specie WHERE name = :name")
    Flowable <List<Specie>> getSpeciesByName(String name);

    @Query("SELECT * FROM specie WHERE url = :specieUrl")
    Flowable<Specie> getSpecieByUrl(String specieUrl);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpecies(List<Specie> specieList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSpecieList(List<Specie> specieList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSpecie(Specie specie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpecie(Specie specie);
}
