package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.StarShip
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StarshipDao: BaseDao<StarShip> {
    @Query("SELECT * FROM starship")
    abstract suspend fun all(): List<StarShip>

    @Query("SELECT * FROM starship ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<StarShip>

    @Query("SELECT * FROM starship LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<StarShip>

    @Query("SELECT * FROM starship WHERE name = :starshipName")
    abstract suspend fun getPeopleByName(starshipName: String): StarShip

    @Query("SELECT * FROM starship WHERE url = :starshipUrl")
    abstract suspend fun getStarshipByURL(starshipUrl: String): StarShip

    @Query("SELECT * FROM starship")
    abstract fun allAsFlow(): Flow<List<StarShip>?>

    @Query("SELECT * FROM starship ORDER BY name ASC")
    abstract fun allAlphabeticallyAsFlow(): Flow<List<StarShip>?>

    @Query("SELECT * FROM starship LIMIT :size")
    abstract fun getStarShipsBySizeAsFlow(size: Int): Flow<List<StarShip>?>

    @Query("SELECT * FROM starship WHERE name = :starshipName")
    abstract fun getStarshipByNameAsFlow(starshipName: String): Flow<StarShip?>

    @Query("SELECT * FROM starship WHERE url = :starshipUrl")
    abstract fun getStarshipByURLAsFlow(starshipUrl: String): Flow<StarShip?>
}
