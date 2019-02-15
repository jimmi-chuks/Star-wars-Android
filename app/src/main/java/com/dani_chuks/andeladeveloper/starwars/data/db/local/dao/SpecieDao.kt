package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie

import io.reactivex.Flowable

@Dao
interface SpecieDao {
    @get:Query("SELECT * FROM specie")
    val all: Flowable<List<Specie>>

    @get:Query("SELECT * FROM specie ORDER BY name ASC")
    val allAlphabetically: Flowable<List<Specie>>

    @Query("SELECT * FROM specie LIMIT :size")
    fun getItemBySize(size: Int): Flowable<List<Specie>>

    @Query("SELECT * FROM specie WHERE name = :name")
    fun getSpeciesByName(name: String): Flowable<List<Specie>>

    @Query("SELECT * FROM specie WHERE url = :specieUrl")
    fun getSpecieByUrl(specieUrl: String): Flowable<Specie>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpecies(specieList: List<Specie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpecieList(specieList: List<Specie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpecie(specie: Specie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSpecie(specie: Specie)
}
