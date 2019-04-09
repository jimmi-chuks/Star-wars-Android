package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class StarshipRepository @Inject
constructor(private val remoteDataSource: StarshipRemoteDataSource,
            private val appDatabase: AppDatabase,
            private val preferenceManager: SharedPreferenceManager) {

    suspend fun all(): List<Starship> {
            return appDatabase.starshipDao().all()
        }

    suspend fun allAlphabetically(): List<Starship> {
            return appDatabase.starshipDao().allAlphabetically()
        }

    suspend fun getItemsLimitedToSize(size: Int): List<Starship> {
        return appDatabase.starshipDao().getItemBySize(size)
    }

    suspend fun getItemByUrl(stringUrl: String): Starship = coroutineScope {
        val starship = async { appDatabase.starshipDao().getStarshipByURL(stringUrl)}.await()
        if(starship == null){
            val starshipId = DbUtils.getLastPathFromUrl(stringUrl)
            val starshipFromRemote = remoteDataSource.getStarshipById(starshipId)
            if(starshipFromRemote is Result.Success) {
                starshipFromRemote.data.let { appDatabase.starshipDao().insert(it) }
            }
        }
        starship
    }

    suspend fun insertItem(starship: Starship) = coroutineScope {
        appDatabase.starshipDao().insert(starship)
    }

    suspend fun insertItemList(starship: List<Starship>)= coroutineScope {
        appDatabase.starshipDao().insertList(starship)
    }

    suspend fun fetchAndSync(page: Int)= coroutineScope {
        val allStarships = remoteDataSource.getStarshipFromPage(page)
        println("fetch and sync star ships from page $page ==> $allStarships")
        if (allStarships is Result.Success) {
            allStarships.data.starship?.let {
                appDatabase.starshipDao().insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME, HomeViewModel.firstPage + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME)
            }
        }
        allStarships
    }
}
