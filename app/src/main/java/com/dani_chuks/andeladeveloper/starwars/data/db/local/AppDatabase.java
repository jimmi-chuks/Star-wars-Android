package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Planet;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Specie;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;


@Database(entities = {Film.class, Person.class, Planet.class, Specie.class, Starship.class, Vehicle.class}, version = 1, exportSchema = false)
@TypeConverters({StringListConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();

    public abstract PersonDao personDao();

    public abstract PlanetDao planetDao();

    public abstract SpecieDao specieDao();

    public abstract StarshipDao starshipDao();

    public abstract VehicleDao vehicleDao();
}
