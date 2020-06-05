package com.dani_chuks.andeladeveloper.starwars.data.db.repository.film

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getFilmsByPredicate(predicate: DaoPredicate): List<Film>

    suspend fun fetchAndSync()

    suspend fun deleteAll(): Int

    suspend fun getFilmByUrl(stringUrl: String): Result<Film>

    suspend fun insertFilm(film: Film)

    fun getFilmsByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Film>?>

    fun allAsFlow(): Flow<List<Film>?>

    fun allAlphabeticallyAsFlow(): Flow<List<Film>?>

    fun getItemBySizeAsFlow(limit: Int): Flow<List<Film>?>
}