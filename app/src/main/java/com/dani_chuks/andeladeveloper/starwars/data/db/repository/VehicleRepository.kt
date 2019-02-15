package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleRepository @Inject
constructor(private val apiService: ApiService,
            private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Vehicle> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Vehicle>>
        get() {
            fetchIfEmpty()
            return appDatabase.vehicleDao().all
        }

    override val allAlphabetically: Flowable<List<Vehicle>>
        get() {
            fetchIfEmpty()
            return appDatabase.vehicleDao().allAlphabetically
        }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Vehicle>> {
        fetchIfEmpty()
        return appDatabase.vehicleDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Vehicle> {
        val vehicleId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getVehicleById(vehicleId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { vehicle -> appDatabase.vehicleDao().insertVehicle(vehicle) })
        return appDatabase.vehicleDao().getVehicleByURL(stringUrl)
    }

    override fun insertItem(vehicle: Vehicle) {
        disposableManager.add(Observable.just(vehicle)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.vehicleDao().insertVehicle(vehicle) })
    }

    override fun insertItemList(vehicle: List<Vehicle>) {
        disposableManager.add(Observable.just(vehicle)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.vehicleDao().insertVehicleList(vehicle) })
    }

    private fun fetchIfEmpty() {
        val firstPage = 1
        disposableManager.add(
                apiService.getVehicleList(firstPage)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.vehicle?.let { appDatabase.vehicleDao().insertVehicleList(it) }
                            preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, firstPage + 1)
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME)
                        })
    }
}
