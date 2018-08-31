package com.dani_chuks.andeladeveloper.starwars.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.ComputationScheduler;
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.DatabaseInfo;
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.IoScheduler;
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.MainScheduler;
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants;
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager;
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase;
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.FilmRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PersonRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.PlanetRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.SpecieRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.StarshipRepository;
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.VehicleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideApplication(Application application) {
        return application;
    }

    @Provides
    @DatabaseInfo
    public String provideDatabaseName() {
        return AppConstants.DATABASE_NAME;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public SharedPreferenceManager providePreferenceManager(@NonNull final SharedPreferences sharedPreferences,
                                                            @NonNull final Context context) {
        return new SharedPreferenceManager(sharedPreferences, context);
    }

    @Provides
    @Reusable
    @MainScheduler
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Reusable
    @IoScheduler
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Reusable
    @ComputationScheduler
    Scheduler providesComputationScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Reusable
    ISchedulerProvider provideSchedulerProvider(@NonNull @MainScheduler final Scheduler mainThreadScheduler,
                                                @NonNull @IoScheduler final Scheduler ioScheduler,
                                                @NonNull @ComputationScheduler final Scheduler computationScheduler) {
        return new SchedulerProvider(mainThreadScheduler, ioScheduler, computationScheduler);
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull final Context context, @NonNull @DatabaseInfo final String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Reusable
    FilmRepository providesFilmRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                          @NonNull final ISchedulerProvider schedulerProvider,
                                          @NonNull final SharedPreferenceManager preferenceManager){
        return  new FilmRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }

    @Provides
    @Reusable
    PersonRepository providesPersonRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                            @NonNull final ISchedulerProvider schedulerProvider,
                                            @NonNull final SharedPreferenceManager preferenceManager){
        return  new PersonRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }

    @Provides
    @Reusable
    PlanetRepository providesPlanetRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                              @NonNull final ISchedulerProvider schedulerProvider,
                                              @NonNull final SharedPreferenceManager preferenceManager){
        return  new PlanetRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }

    @Provides
    @Reusable
    VehicleRepository providesVehicleRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                                @NonNull final ISchedulerProvider schedulerProvider,
                                                @NonNull final SharedPreferenceManager preferenceManager){
        return  new VehicleRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }

    @Provides
    @Reusable
    SpecieRepository providesSpecieRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                              @NonNull final ISchedulerProvider schedulerProvider,
                                              @NonNull final SharedPreferenceManager preferenceManager){
        return  new SpecieRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }

    @Provides
    @Reusable
    StarshipRepository providesStarshipRepository(@NonNull final ApiService apiService, @NonNull AppDatabase appDatabase,
                                              @NonNull final ISchedulerProvider schedulerProvider,
                                              @NonNull final SharedPreferenceManager preferenceManager){
        return  new StarshipRepository(apiService,appDatabase, schedulerProvider, preferenceManager);
    }
}
