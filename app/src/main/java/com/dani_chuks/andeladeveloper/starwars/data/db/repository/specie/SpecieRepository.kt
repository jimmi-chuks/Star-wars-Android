package com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow

interface SpecieRepository {

    suspend fun getSpeciesByPredicate(predicate: DaoPredicate): List<Specie>

    suspend fun all(): List<Specie>

    suspend fun allAlphabetically(): List<Specie>

    suspend fun getItemsLimitedToSize(size: Int): List<Specie>

    suspend fun getItemByUrl(stringUrl: String): Specie?

    suspend fun insertItem(t: Specie)

    suspend fun insertItemList(t: List<Specie>)

    suspend fun fetchAndSync(page: Int)

    fun getSpeciesByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Specie>?>

    fun allAsFlow(): Flow<List<Specie>?>

    fun allAlphabeticallyAsFlow(): Flow<List<Specie>?>

    fun getItemBySizeAsFlow(size: Int): Flow<List<Specie>?>

    fun getSpeciesByNameAsFlow(name: String): Flow<List<Specie>?>

    fun getSpecieByUrlAsFlow(specieUrl: String): Flow<Specie?>

}
