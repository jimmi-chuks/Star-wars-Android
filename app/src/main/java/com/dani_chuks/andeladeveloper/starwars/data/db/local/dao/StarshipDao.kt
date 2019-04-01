package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship

@Dao
abstract class StarshipDao: BaseDao<Starship> {
    @Query("SELECT * FROM starship")
    abstract suspend fun all(): List<Starship>

    @Query("SELECT * FROM starship ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Starship>

    @Query("SELECT * FROM starship LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<Starship>

    @Query("SELECT * FROM starship WHERE name = :starshipName")
    abstract suspend fun getPeopleByName(starshipName: String): Starship

    @Query("SELECT * FROM starship WHERE url = :starshipUrl")
    abstract suspend fun getStarshipByURL(starshipUrl: String): Starship
}
