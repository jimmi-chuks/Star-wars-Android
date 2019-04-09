package com.dani_chuks.andeladeveloper.starwars.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.DatabaseInfo
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.DefaultDispatcher
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.IoDispatcher
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.MainDispatcher
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @Reusable
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Reusable
    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @Reusable
    fun provideDispatcherProvider(@MainDispatcher ui: CoroutineDispatcher,
                                  @IoDispatcher io: CoroutineDispatcher,
                                  @DefaultDispatcher computation: CoroutineDispatcher): IDispatcherProvider {
        return DispatchersProvider(ui, io, computation)
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
    fun providesPersonRemoteDataSource(apiService: ApiService): PersonRemoteDataSource {
        return PersonRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesPersonRepository(appDatabase: AppDatabase,
                                          personRemoteDataSource: PersonRemoteDataSource,
                                          preferenceManager: SharedPreferenceManager): PersonRepository {
        return PersonRepository(appDatabase, personRemoteDataSource, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesFilmRemoteDataSource(apiService: ApiService): FilmRemoteDataSource {
        return FilmRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesFilmRepository(appDatabase: AppDatabase,
                               filmRemoteDataSource: FilmRemoteDataSource,
                               preferenceManager: SharedPreferenceManager): FilmRepository {
        return FilmRepository(appDatabase, filmRemoteDataSource, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesPlanetRemoteDataSource(apiService: ApiService): PlanetRemoteDataSource {
        return PlanetRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesPlanetRepository(planetRemoteDataSource: PlanetRemoteDataSource,
                                 appDatabase: AppDatabase,
                                 preferenceManager: SharedPreferenceManager): PlanetRepository {
        return PlanetRepository(planetRemoteDataSource, appDatabase, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesSpecieRemoteDataSource(apiService: ApiService): SpecieRemoteDataSource {
        return SpecieRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesSpecieRepository(appDatabase: AppDatabase,
                                 specieRemoteDataSource: SpecieRemoteDataSource,
                                 preferenceManager: SharedPreferenceManager): SpecieRepository {
        return SpecieRepository(appDatabase, specieRemoteDataSource, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesVehicleRemoteDataSource(apiService: ApiService): VehicleRemoteDataSource {
        return VehicleRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesVehicleRepository(vehicleRemoteDataSource: VehicleRemoteDataSource,
                                  appDatabase: AppDatabase,
                                  preferenceManager: SharedPreferenceManager): VehicleRepository {
        return VehicleRepository(vehicleRemoteDataSource, appDatabase, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesStarshipRemoteDataSource(apiService: ApiService): StarshipRemoteDataSource {
        return StarshipRemoteDataSource(apiService)
    }

    @Provides
    @Reusable
    fun providesStarshipRepository(remoteDataSource: StarshipRemoteDataSource,
                                   appDatabase: AppDatabase,
                                   preferenceManager: SharedPreferenceManager): StarshipRepository {
        return StarshipRepository(remoteDataSource, appDatabase, preferenceManager)
    }
}
