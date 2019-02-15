package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import io.reactivex.Flowable

@Dao
interface PlanetDao {
    @get:Query("SELECT * FROM planet")
    val all: Flowable<List<Planet>>

    @get:Query("SELECT * FROM planet ORDER BY name ASC")
    val allAlphabetically: Flowable<List<Planet>>

    @Query("SELECT * FROM planet LIMIT :size")
    fun getItemBySize(size: Int): Flowable<List<Planet>>

    @Query("SELECT * FROM planet WHERE name = :name")
    fun getplanetsByName(name: String): Flowable<List<Planet>>

    @Query("SELECT * FROM planet WHERE url = :planetUrl")
    fun getplanetByURL(planetUrl: String): Flowable<Planet>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateplanets(planetList: List<Planet>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlanetList(planetList: List<Planet>): LongArray

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlanet(planet: Planet)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateplanet(planet: Planet)
}
