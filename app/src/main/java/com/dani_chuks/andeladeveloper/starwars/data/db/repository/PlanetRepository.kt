package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PlanetRepository @Inject
constructor(private val apiService: ApiService,
            private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Planet> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Planet>>
        get() {
            fetchIfEmpty()
            return appDatabase.planetDao().all
        }

    override val allAlphabetically: Flowable<List<Planet>>
        get() {
            fetchIfEmpty()
            return appDatabase.planetDao().allAlphabetically
        }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Planet>> {
        fetchIfEmpty()
        return appDatabase.planetDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Planet> {
        val personId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getPlanetById(personId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { film -> appDatabase.planetDao().insertPlanet(film) })

        return appDatabase.planetDao().getplanetByURL(stringUrl)
    }

    override fun insertItem(planet: Planet) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { time -> appDatabase.planetDao().insertPlanet(planet) })
    }

    override fun insertItemList(planets: List<Planet>) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { time -> appDatabase.planetDao().insertPlanetList(planets) })
    }

    private fun fetchIfEmpty() {
        //        if (preferenceManager.isDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME)) {
        //            final int firstPage = 1;
        //            disposableManager.add(
        //                    apiService.getPlanetList(firstPage)
        //                            .subscribeOn(schedulerProvider.getIoScheduler())
        //                            .subscribe(response ->
        //                            {
        //                                appDatabase.planetDao().insertPlanetList(response.getPlanet());
        //                                preferenceManager.setResourceNextPage(AppConstants.PLANET_RESOURCE_NAME, (firstPage + 1));
        //                                preferenceManager.setDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME);
        //                            }));
        //        }
        val firstPage = 1
        disposableManager.add(
                apiService.getPlanetList(firstPage)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.planet?.let { appDatabase.planetDao().insertPlanetList(it) }
                            preferenceManager.setResourceNextPage(AppConstants.PLANET_RESOURCE_NAME, firstPage + 1)
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.PLANET_RESOURCE_NAME)
                        })
    }
}
