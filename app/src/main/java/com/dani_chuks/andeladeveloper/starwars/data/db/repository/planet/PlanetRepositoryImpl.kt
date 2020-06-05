package com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
        val remoteDataSource: PlanetRemoteDataSource,
        val appDatabase: AppDatabase,
        val preferenceManager: SharedPreferenceManager) : PlanetRepository {

    val planetDao = appDatabase.planetDao()

    override suspend fun all(): List<Planet> {
        return planetDao.all()
    }

    override fun allAsFlow(): Flow<List<Planet>?> {
        return planetDao.allAsFlow()
    }

    override suspend fun allAlphabetically(): List<Planet> {
        return planetDao.allAlphabetically()
    }

    override suspend fun getItemsLimitedToSize(size: Int): List<Planet> {
        return planetDao.getItemBySize(size)
    }

    override suspend fun getItemByUrl(stringUrl: String): Planet? = coroutineScope {
        val planet = planetDao.getPlanetByURL(stringUrl)
        if (planet == null) {
            val planetId = DbUtils.getLastPathFromUrl(stringUrl)
            val planetFromRemote = remoteDataSource.getPlanetById(planetId)
            if (planetFromRemote is Result.Success) {
                planetFromRemote.data.let { planetDao.insert(it) }
            }
        }
        planet
    }

    override suspend fun insertItem(planet: Planet) = coroutineScope {
        planetDao.insert(planet)
    }

    override suspend fun insertItemList(planets: List<Planet>) = coroutineScope {
        planetDao.insertList(planets)
    }

    override suspend fun fetchAndSync(page: Int): Result<Boolean> = coroutineScope {
        val allPlanets = remoteDataSource.getAllPlanets(page)
        println("fetch and sync Planets from page $page ==> $allPlanets")
        when(allPlanets){
            is Result.Success -> {
                allPlanets.data.list?.let {
                    planetDao.insertList(it)
                    preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)
                }
                Result.Success(true)
            }
            is Result.Error ->  Result.Error(allPlanets.exception)
        }
    }
}
