package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PlanetRepository @Inject constructor(
        val remoteDataSource: PlanetRemoteDataSource,
        val appDatabase: AppDatabase,
        val preferenceManager: SharedPreferenceManager) {

    suspend fun all(): List<Planet> {
        return appDatabase.planetDao().all()
    }

    suspend fun allAlphabetically(): List<Planet> {
        return appDatabase.planetDao().allAlphabetically()
    }

    suspend fun getItemsLimitedToSize(size: Int): List<Planet> {
        return appDatabase.planetDao().getItemBySize(size)
    }

    suspend fun getItemByUrl(stringUrl: String): Planet = coroutineScope {
        val planet = async { appDatabase.planetDao().getPlanetByURL(stringUrl) }.await()
        if (planet == null) {
            val planetId = DbUtils.getLastPathFromUrl(stringUrl)
            val planetFromRemote = remoteDataSource.getPlanetById(planetId)
            if (planetFromRemote is Result.Success) {
                planetFromRemote.data.let { appDatabase.planetDao().insert(it) }
            }
        }
        planet
    }

    suspend fun insertItem(planet: Planet) = coroutineScope {
        appDatabase.planetDao().insert(planet)
    }

    suspend fun insertItemList(planets: List<Planet>) = coroutineScope {
        appDatabase.planetDao().insertList(planets)
    }

    suspend fun fetchAndSync(page: Int) = coroutineScope {
        val allPlanets = remoteDataSource.getAllPlanets(page)
        println("fetch and sync Planets from page $page ==> $allPlanets")
        if (allPlanets is Result.Success) {
            allPlanets.data.planet?.let {
                appDatabase.planetDao().insertList(it)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)
            }

        }
        allPlanets
    }
}
