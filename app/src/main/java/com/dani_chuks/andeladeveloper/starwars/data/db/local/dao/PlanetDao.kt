package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet

@Dao
abstract class PlanetDao: BaseDao<Planet> {
    @Query("SELECT * FROM planet")
    abstract suspend fun all(): List<Planet>

    @Query("SELECT * FROM planet ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Planet>

    @Query("SELECT * FROM planet LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<Planet>

    @Query("SELECT * FROM planet WHERE name = :name")
    abstract suspend fun getPlanetsByName(name: String): List<Planet>

    @Query("SELECT * FROM planet WHERE url = :planetUrl")
    abstract suspend fun getPlanetByURL(planetUrl: String): Planet
}
