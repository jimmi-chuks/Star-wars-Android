package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpecieRepository @Inject
constructor(private val apiService: ApiService,
            private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Specie> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Specie>>
        get() {
            fetchIfEmpty()
            return appDatabase.specieDao().all
        }

    override val allAlphabetically: Flowable<List<Specie>>
        get() {
            fetchIfEmpty()
            return appDatabase.specieDao().allAlphabetically
        }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Specie>> {
        fetchIfEmpty()
        return appDatabase.specieDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Specie> {
        val specieId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getSpecieById(specieId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { specie -> appDatabase.specieDao().insertSpecie(specie) })

        return appDatabase.specieDao().getSpecieByUrl(stringUrl)
    }

    override fun insertItem(t: Specie) {
        disposableManager.add(Observable.just(t)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.specieDao().insertSpecie(t) })
    }

    override fun insertItemList(t: List<Specie>) {
        disposableManager.add(Observable.just(t)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.specieDao().insertSpecieList(t) })
    }

    private fun fetchIfEmpty() {
        val firstPage = 1
        disposableManager.add(
                apiService.getSpecieList(firstPage)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.specie?.let { appDatabase.specieDao().insertSpecieList(it) }
                            preferenceManager.setResourceNextPage(AppConstants.SPECIE_RESOURCE_NAME, firstPage + 1)
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.SPECIE_RESOURCE_NAME)
                        })
    }
}
