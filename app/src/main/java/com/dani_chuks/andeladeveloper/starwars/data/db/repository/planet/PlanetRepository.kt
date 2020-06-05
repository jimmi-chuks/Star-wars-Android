package com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow


interface PlanetRepository{

    suspend fun fetchAndSync(page: Int): Result<Boolean>

    suspend fun insertItemList(planets: List<Planet>)

    suspend fun insertItem(planet: Planet)

    suspend fun getItemByUrl(stringUrl: String): Planet?

    suspend fun getItemsLimitedToSize(size: Int): List<Planet>

    suspend fun all(): List<Planet>

    fun allAsFlow(): Flow<List<Planet>?>

    suspend fun allAlphabetically(): List<Planet>
}