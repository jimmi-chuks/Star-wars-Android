package com.dani_chuks.andeladeveloper.starwars.data.db.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FilmDao {
    @Query("SELECT * FROM film")
    Flowable<List<Film>> getAll();

    @Query("SELECT * FROM film LIMIT 7")
    Flowable<List<Film>> getFirstSeven();

    @Query("SELECT * FROM film ORDER BY title ASC" )
    Flowable<List<Film>> getAllAlphabetically();

    @Query("SELECT * FROM film WHERE title IN (:titles)")
    Flowable <List<Film>> getFilmsFromTitle(List<String> titles);

    @Query("SELECT * FROM film WHERE url = :filmUrl")
    Flowable<Film> getFilmByUrl(String filmUrl);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFilms(List<Film> films);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFilms(List<Film> films);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(Film film);
}
