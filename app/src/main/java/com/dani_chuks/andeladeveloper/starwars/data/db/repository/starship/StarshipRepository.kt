package com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.StarShip
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow

interface StarshipRepository {

    suspend fun getStarshipsByPredicate(predicate: DaoPredicate): List<StarShip>

    suspend fun all(): List<StarShip>

    suspend fun allAlphabetically(): List<StarShip>

    suspend fun getStarShipsLimitedToSize(size: Int): List<StarShip>

    suspend fun getItemByUrl(stringUrl: String): StarShip

    suspend fun insertItem(starShip: StarShip)

    suspend fun insertItemList(starShip: List<StarShip>)

    suspend fun fetchAndSync(page: Int): Result<EntityList<StarShip>>

    fun getStarshipsByPredicateAsFlow(predicate: DaoPredicate): Flow<List<StarShip>?>

    fun allAsFlow(): Flow<List<StarShip>?>

    fun allAlphabeticallyAsFlow(): Flow<List<StarShip>?>

    fun getStarShipsLimitedToSizeAsFlow(size: Int): Flow<List<StarShip>?>

    fun getStarShipByNameAsFlow(name: String): Flow<StarShip?>

    fun getStarShipByURLAsFlow(url: String): Flow<StarShip?>
}
