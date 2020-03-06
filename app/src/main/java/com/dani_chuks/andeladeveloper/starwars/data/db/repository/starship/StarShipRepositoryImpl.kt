package com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllAlphabetically
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllBySize
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.StarShip
import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarShipRepositoryImpl  @Inject
constructor(private val remoteDataSource: StarshipRemoteDataSource,
            private val appDatabase: AppDatabase,
            private val preferenceManager: SharedPreferenceManager): StarshipRepository {

    val starShipDao = appDatabase.starshipDao()

    override suspend fun getStarshipsByPredicate(predicate: DaoPredicate): List<StarShip> {
        return when (predicate) {
            is GetAllAlphabetically -> starShipDao.allAlphabetically()
            is GetAllBySize -> starShipDao.getItemBySize(predicate.size)
            else -> starShipDao.all()
        }
    }

    override suspend fun all(): List<StarShip> {
        return starShipDao.all()
    }

    override suspend fun allAlphabetically(): List<StarShip> {
        return starShipDao.allAlphabetically()
    }

    override suspend fun getStarShipsLimitedToSize(size: Int): List<StarShip> {
        return starShipDao.getItemBySize(size)
    }

    override suspend fun getItemByUrl(stringUrl: String): StarShip = coroutineScope {
        val starship = starShipDao.getStarshipByURL(stringUrl)
        if(starship == null){
            val starshipId = DbUtils.getLastPathFromUrl(stringUrl)
            val starshipFromRemote = remoteDataSource.getStarshipById(starshipId)
            if(starshipFromRemote is Result.Success) {
                starshipFromRemote.data.let { starShipDao.insert(it) }
            }
        }
        starship
    }

    override suspend fun insertItem(starShip: StarShip) = coroutineScope {
        starShipDao.insert(starShip)
    }

    override suspend fun insertItemList(starShip: List<StarShip>)= coroutineScope {
        starShipDao.insertList(starShip)
    }

    override suspend fun fetchAndSync(page: Int): Result<EntityList<StarShip>> = coroutineScope {
        val allStarships = remoteDataSource.getStarshipFromPage(page)
        println("fetch and sync star ships from page $page ==> $allStarships")
        if (allStarships is Result.Success) {
            allStarships.data.list?.let {
                starShipDao.insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME, HomeViewModel.firstPage + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME)
            }
        }
        allStarships
    }

    override fun getStarshipsByPredicateAsFlow(predicate: DaoPredicate): Flow<List<StarShip>?> {
        return when (predicate) {
            is GetAllAlphabetically -> starShipDao.allAlphabeticallyAsFlow()
            is GetAllBySize -> starShipDao.getStarShipsBySizeAsFlow(predicate.size)
            else -> starShipDao.allAsFlow()
        }
    }

    override fun allAsFlow(): Flow<List<StarShip>?> = starShipDao.allAsFlow()

    override fun allAlphabeticallyAsFlow(): Flow<List<StarShip>?> = starShipDao.allAlphabeticallyAsFlow()

    override fun getStarShipsLimitedToSizeAsFlow(size: Int): Flow<List<StarShip>?> = starShipDao.getStarShipsBySizeAsFlow(size)

    override fun getStarShipByNameAsFlow(name: String): Flow<StarShip?> = starShipDao.getStarshipByNameAsFlow(name)

    override fun getStarShipByURLAsFlow(url: String): Flow<StarShip?> = starShipDao.getStarshipByURLAsFlow(url)
}
