package com.dani_chuks.andeladeveloper.starwars.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.ComputationScheduler
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.DatabaseInfo
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.IoScheduler
import com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers.MainScheduler
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: Application): Context {
        return application
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DATABASE_NAME
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providePreferenceManager(sharedPreferences: SharedPreferences,
                                 context: Context): SharedPreferenceManager {
        return SharedPreferenceManager(sharedPreferences, context)
    }

    @Provides
    @Reusable
    @MainScheduler
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Reusable
    @IoScheduler
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Reusable
    @ComputationScheduler
    fun providesComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @Reusable
    fun provideSchedulerProvider(@MainScheduler mainThreadScheduler: Scheduler,
                                          @IoScheduler ioScheduler: Scheduler,
                                          @ComputationScheduler computationScheduler: Scheduler): ISchedulerProvider {
        return SchedulerProvider(mainThreadScheduler, ioScheduler, computationScheduler)
    }


    @Provides
    @Reusable
    fun provideAppDatabase(context: Context, @DatabaseInfo databaseName: String): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Reusable
    fun providesPersonRepository(apiService: ApiService, appDatabase: AppDatabase,
                                          schedulerProvider: ISchedulerProvider,
                                          preferenceManager: SharedPreferenceManager): PersonRepository {
        return PersonRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }


    @Provides
    @Reusable
    fun providesFilmRepository(apiService: ApiService, appDatabase: AppDatabase,
                               schedulerProvider: ISchedulerProvider,
                               preferenceManager: SharedPreferenceManager): FilmRepository {
        return FilmRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesPlanetRepository(apiService: ApiService, appDatabase: AppDatabase,
                                          schedulerProvider: ISchedulerProvider,
                                          preferenceManager: SharedPreferenceManager): PlanetRepository {
        return PlanetRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesVehicleRepository(apiService: ApiService, appDatabase: AppDatabase,
                                           schedulerProvider: ISchedulerProvider,
                                           preferenceManager: SharedPreferenceManager): VehicleRepository {
        return VehicleRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesSpecieRepository(apiService: ApiService, appDatabase: AppDatabase,
                                          schedulerProvider: ISchedulerProvider,
                                          preferenceManager: SharedPreferenceManager): SpecieRepository {
        return SpecieRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesStarshipRepository(apiService: ApiService, appDatabase: AppDatabase,
                                            schedulerProvider: ISchedulerProvider,
                                            preferenceManager: SharedPreferenceManager): StarshipRepository {
        return StarshipRepository(apiService, appDatabase, schedulerProvider, preferenceManager)
    }
}
