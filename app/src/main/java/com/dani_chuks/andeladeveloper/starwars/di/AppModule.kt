package com.dani_chuks.andeladeveloper.starwars.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.film.FilmRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.film.FilmRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.film.FilmRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.people.PeopleRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.people.PeopleRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.people.PersonRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet.PlanetRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet.PlanetRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.planet.PlanetRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie.SpecieRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie.SpecieRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.specie.SpecieRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship.StarShipRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship.StarshipRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.starship.StarshipRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle.VehicleRemoteDataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle.VehicleRepository
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.vehicle.VehicleRepositoryImpl
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.DatabaseInfo
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.DefaultDispatcher
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.IoDispatcher
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun providesPeopleRepository(appDatabase: AppDatabase,
                                 personRemoteDataSource: PersonRemoteDataSource,
                                 preferenceManager: SharedPreferenceManager): PeopleRepository {
        return PeopleRepositoryImpl(appDatabase, personRemoteDataSource, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesFilmRepository(appDatabase: AppDatabase,
                               filmRemoteDataSource: FilmRemoteDataSource,
                               preferenceManager: SharedPreferenceManager): FilmRepository {
        return FilmRepositoryImpl(appDatabase, filmRemoteDataSource, preferenceManager)
    }


    @Provides
    @Reusable
    fun providesPlanetRepository(planetRemoteDataSource: PlanetRemoteDataSource,
                                 appDatabase: AppDatabase,
                                 preferenceManager: SharedPreferenceManager): PlanetRepository {
        return PlanetRepositoryImpl(planetRemoteDataSource, appDatabase, preferenceManager)
    }

    @ExperimentalCoroutinesApi
    @Provides
    @Reusable
    fun providesSpecieRepository(appDatabase: AppDatabase,
                                 specieRemoteDataSource: SpecieRemoteDataSource,
                                 preferenceManager: SharedPreferenceManager): SpecieRepository {
        return SpecieRepositoryImpl(appDatabase, specieRemoteDataSource, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesVehicleRepository(vehicleRemoteDataSource: VehicleRemoteDataSource,
                                  appDatabase: AppDatabase,
                                  preferenceManager: SharedPreferenceManager): VehicleRepository {
        return VehicleRepositoryImpl(vehicleRemoteDataSource, appDatabase, preferenceManager)
    }

    @Provides
    @Reusable
    fun providesStarshipRepository(remoteDataSource: StarshipRemoteDataSource,
                                   appDatabase: AppDatabase,
                                   preferenceManager: SharedPreferenceManager): StarshipRepository {
        return StarShipRepositoryImpl(remoteDataSource, appDatabase, preferenceManager)
    }
}
