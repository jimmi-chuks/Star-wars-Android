package com.dani_chuks.andeladeveloper.starwars.data.db.repository;

import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource;
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class VehicleRepository implements DataSource<Vehicle> {

    @NonNull
    private final ApiService apiService;
    @NonNull
    private final AppDatabase appDatabase;
    @NonNull
    private final ISchedulerProvider schedulerProvider;
    @NonNull
    private final CompositeDisposable disposableManager = new CompositeDisposable();
    private final SharedPreferenceManager preferenceManager;

    @Inject
    public VehicleRepository(@NonNull final ApiService apiService,
                             @NonNull final AppDatabase appDatabase,
                             @NonNull final ISchedulerProvider schedulerProvider,
                             final SharedPreferenceManager preferenceManager) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
        this.schedulerProvider = schedulerProvider;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public Flowable<List<Vehicle>> getAll() {
        fetchIfEmpty();
        return appDatabase.vehicleDao().getAll();
    }

    @Override
    public Flowable<List<Vehicle>> getItemsLimitedToSize(final int size) {
        fetchIfEmpty();
        return appDatabase.vehicleDao().getItemBySize(size);
    }

    @Override
    public Flowable<List<Vehicle>> getAllAlphabetically() {
        fetchIfEmpty();
        return appDatabase.vehicleDao().getAllAlphabetically();
    }

    @Override
    public Flowable<Vehicle> getItemByUrl(final String stringUrl) {
        int vehicleId = DbUtils.getLastPathFromUrl(stringUrl);
        disposableManager.add(
                apiService.getVehicleById(vehicleId)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(vehicle -> appDatabase.vehicleDao().insertVehicle(vehicle)));
        return appDatabase.vehicleDao().getVehicleByURL(stringUrl);
    }

    @Override
    public void insertItem(final Vehicle vehicle) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.vehicleDao().insertVehicle(vehicle)));
    }

    @Override
    public void insertItemList(final List<Vehicle> vehicle) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> appDatabase.vehicleDao().insertVehicleList(vehicle)));
    }

    private void fetchIfEmpty() {
//        if (preferenceManager.isDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME)) {
//            final int firstPage = 1;
//            disposableManager.add(
//                    apiService.getVehicleList(firstPage)
//                            .subscribeOn(schedulerProvider.getIoScheduler())
//                            .subscribe(response ->
//                            {
//                                appDatabase.vehicleDao().insertVehicleList(response.getVehicle());
//                                preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, (firstPage + 1));
//                                preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME);
//                            }));
//        }
        final int firstPage = 1;
        disposableManager.add(
                apiService.getVehicleList(firstPage)
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response ->
                        {
                            appDatabase.vehicleDao().insertVehicleList(response.getVehicle());
                            preferenceManager.setResourceNextPage(AppConstants.VEHICLE_RESOURCE_NAME, (firstPage + 1));
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.VEHICLE_RESOURCE_NAME);
                        }));
    }
}
