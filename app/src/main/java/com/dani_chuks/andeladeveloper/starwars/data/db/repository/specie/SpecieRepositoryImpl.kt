package com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllAlphabetically
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllBySize
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import com.dani_chuks.andeladeveloper.starwars.di.Result
import com.dani_chuks.andeladeveloper.starwars.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class SpecieRepositoryImpl @Inject
constructor(private val appDatabase: AppDatabase,
            private val remoteDataSource: SpecieRemoteDataSource,
            private val preferenceManager: SharedPreferenceManager): SpecieRepository {

    val specieDao = appDatabase.specieDao()

    override suspend fun getSpeciesByPredicate(predicate: DaoPredicate): List<Specie> {
        return when (predicate) {
            is GetAllAlphabetically -> specieDao.allAlphabetically()
            is GetAllBySize -> specieDao.getItemBySize(predicate.size)
            else -> specieDao.all()
        }
    }

    override suspend fun all(): List<Specie> {
        return specieDao.all()
    }

    override suspend fun allAlphabetically(): List<Specie> = coroutineScope {
        specieDao.allAlphabetically()
    }

    override suspend fun getItemsLimitedToSize(size: Int): List<Specie> = coroutineScope {
        specieDao.getItemBySize(size)
    }

    override suspend fun getItemByUrl(stringUrl: String): Specie? = coroutineScope {
        val specie = specieDao.getSpecieByUrl(stringUrl)
        if (specie == null) {
            val specieId = DbUtils.getLastPathFromUrl(stringUrl)
            val specieFromRemote = remoteDataSource.getPersonById(specieId)
            if (specieFromRemote is Result.Success) {
                specieFromRemote.data.let { specieDao.insert(it) }
            }
        }
        specie
    }

    override suspend fun insertItem(t: Specie) = coroutineScope {
        specieDao.insert(t)
    }

    override suspend fun insertItemList(t: List<Specie>) = coroutineScope {
        specieDao.insertList(t)
    }

    override suspend fun fetchAndSync(page: Int): Result<EntityList<Specie>> = coroutineScope {
        val speciesFetched = remoteDataSource.getSpeciesFromPage(page)
        if (speciesFetched is Result.Success) {
            speciesFetched.data.list?.let {
                specieDao.insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME, HomeViewModel.firstPage + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME)
            }
        }
        speciesFetched
    }

    override fun getSpeciesByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Specie>?> {
        return when (predicate) {
            is GetAllAlphabetically -> specieDao.allAlphabeticallyAsFlow()
            is GetAllBySize -> specieDao.getItemBySizeAsFlow(predicate.size)
            else -> specieDao.allAsFlow()
        }
    }

    override fun allAsFlow(): Flow<List<Specie>?> = specieDao.allAsFlow()

    override fun allAlphabeticallyAsFlow(): Flow<List<Specie>?> = specieDao.allAlphabeticallyAsFlow()

    override fun getItemBySizeAsFlow(size: Int): Flow<List<Specie>?> = specieDao.getItemBySizeAsFlow(size)

    override fun getSpeciesByNameAsFlow(name: String): Flow<List<Specie>?> = specieDao.getSpeciesByNameAsFlow(name)

    override fun getSpecieByUrlAsFlow(specieUrl: String): Flow<Specie?> = specieDao.getSpecieByUrlAsFlow(specieUrl)

}