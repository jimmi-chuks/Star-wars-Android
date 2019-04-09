package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film

@Dao
abstract class FilmDao: BaseDao<Film> {
    @Query("SELECT * FROM film")
    abstract suspend fun all():List<Film>

    @Query("SELECT * FROM film ORDER BY title ASC")
    abstract suspend fun allAlphabetically(): List<Film>

    @Query("SELECT * FROM film LIMIT :limit")
    abstract suspend fun getItemBySize(limit: Int): List<Film>

    @Query("SELECT * FROM film WHERE title IN (:titles)")
    abstract suspend fun getFilmsFromTitle(titles: List<String>): List<Film>

    @Query("SELECT * FROM film WHERE url = :filmUrl")
    abstract suspend fun getFilmByUrl(filmUrl: String): Film?

    @Query("DELETE FROM film")
    abstract fun clearTable(): Int


//    @Query("UPDATE film SET producer = :newValue WHERE producer = :url")
//    abstract suspend fun updateTitle(url: String, newValue: String)
}
