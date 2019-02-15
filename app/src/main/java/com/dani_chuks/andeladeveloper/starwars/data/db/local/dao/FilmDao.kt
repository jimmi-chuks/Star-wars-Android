package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import io.reactivex.Flowable

@Dao
interface FilmDao {
    @get:Query("SELECT * FROM film")
    val all: Flowable<List<Film>>

    @get:Query("SELECT * FROM film ORDER BY title ASC")
    val allAlphabetically: Flowable<List<Film>>

    @Query("SELECT * FROM film LIMIT :limit")
    fun getItemBySize(limit: Int): Flowable<List<Film>>

    @Query("SELECT * FROM film WHERE title IN (:titles)")
    fun getFilmsFromTitle(titles: List<String>): Flowable<List<Film>>

    @Query("SELECT * FROM film WHERE url = :filmUrl")
    fun getFilmByUrl(filmUrl: String): Flowable<Film>

    @Query("SELECT * FROM film WHERE url = :filmUrl")
    fun getFilmByUrlAsSingle(filmUrl: String): Flowable<Film>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(films: List<Film>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFilms(films: List<Film>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Film)
}
