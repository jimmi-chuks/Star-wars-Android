package com.dani_chuks.andeladeveloper.starwars.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dani_chuks.andeladeveloper.starwars.data.db.StringListConverter
import com.dani_chuks.andeladeveloper.starwars.data.db.local.dao.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.*


@Database(entities = [Film::class, Person::class, Planet::class, Specie::class, Starship::class, Vehicle::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

    abstract fun personDao(): PersonDao

    abstract fun planetDao(): PlanetDao

    abstract fun specieDao(): SpecieDao

    abstract fun starshipDao(): StarshipDao

    abstract fun vehicleDao(): VehicleDao
}
