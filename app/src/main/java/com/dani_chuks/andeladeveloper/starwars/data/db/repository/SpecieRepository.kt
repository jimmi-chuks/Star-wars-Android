package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.Result
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.SpecieList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModel.Companion.firstPage
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SpecieRepository @Inject
constructor(private val appDatabase: AppDatabase,
            private val remoteDataSource: SpecieRemoteDataSource,
            private val preferenceManager: SharedPreferenceManager) {
    private val disposableManager = CompositeDisposable()

    suspend fun all(): List<Specie>  {
            return appDatabase.specieDao().all()
        }

    suspend fun allAlphabetically(): List<Specie> = coroutineScope {
        appDatabase.specieDao().allAlphabetically()
    }

    suspend fun getItemsLimitedToSize(size: Int): List<Specie> = coroutineScope {
        appDatabase.specieDao().getItemBySize(size)
    }

    suspend fun getItemByUrl(stringUrl: String): Specie = coroutineScope {
        val specie = async { appDatabase.specieDao().getSpecieByUrl(stringUrl) }.await()
        if(specie == null){
            val specieId = DbUtils.getLastPathFromUrl(stringUrl)
            val specieFromRemote = remoteDataSource.getPersonById(specieId)
            if(specieFromRemote is Result.Success) {
                specieFromRemote.data.let { appDatabase.specieDao().insert(it) }
            }
        }
        specie
    }

    suspend fun insertItem(t: Specie) {
        appDatabase.specieDao().insert(t)
    }

    suspend fun insertItemList(t: List<Specie>) = coroutineScope{
        appDatabase.specieDao().insertList(t)
    }

    suspend fun fetchAndSync(page: Int): Result<SpecieList> = coroutineScope {
        val speciesFetched = remoteDataSource.getSpeciesFromPage(page)
        println("fetch and sync Species from page $page ==> $speciesFetched")
        if (speciesFetched is Result.Success) {
            speciesFetched.data.specie?.let {
                appDatabase.specieDao().insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME, firstPage + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME)
            }
        }
        speciesFetched
    }

}
