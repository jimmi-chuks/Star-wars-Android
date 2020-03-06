package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SpecieDao: BaseDao<Specie> {
    @Query("SELECT * FROM specie")
    abstract suspend fun all(): List<Specie>

    @Query("SELECT * FROM specie ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Specie>

    @Query("SELECT * FROM specie LIMIT :size")
    abstract suspend fun getItemBySize(size: Int): List<Specie>

    @Query("SELECT * FROM specie WHERE name = :name")
    abstract suspend fun getSpeciesByName(name: String): List<Specie>

    @Query("SELECT * FROM specie WHERE url = :specieUrl")
    abstract suspend fun getSpecieByUrl(specieUrl: String): Specie?

    @Query("SELECT * FROM specie")
    abstract fun allAsFlow(): Flow<List<Specie>?>

    @Query("SELECT * FROM specie ORDER BY name ASC")
    abstract fun allAlphabeticallyAsFlow(): Flow<List<Specie>?>

    @Query("SELECT * FROM specie LIMIT :size")
    abstract fun getItemBySizeAsFlow(size: Int): Flow<List<Specie>?>

    @Query("SELECT * FROM specie WHERE name = :name")
    abstract fun getSpeciesByNameAsFlow(name: String): Flow<List<Specie>?>

    @Query("SELECT * FROM specie WHERE url = :specieUrl")
    abstract fun getSpecieByUrlAsFlow(specieUrl: String): Flow<Specie?>
}
