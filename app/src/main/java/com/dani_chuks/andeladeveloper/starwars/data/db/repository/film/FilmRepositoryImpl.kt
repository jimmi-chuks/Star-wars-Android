package com.dani_chuks.andeladeveloper.starwars.data.db.repository.film

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllAlphabetically
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.GetAllBySize
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
        val appDatabase: AppDatabase,
        val filmRemoteDataSource: FilmRemoteDataSource,
        private val preferenceManager: SharedPreferenceManager
) : FilmRepository {

    val filmDao = appDatabase.filmDao()

    override suspend fun getFilmsByPredicate(predicate: DaoPredicate): List<Film> {
        return when (predicate) {
            is GetAllAlphabetically -> filmDao.allAlphabetically()
            is GetAllBySize -> filmDao.getItemBySize(predicate.size)
            else -> filmDao.all()
        }
    }

    suspend fun all(): List<Film> {
        return filmDao.all()
    }

    suspend fun allAlphabetically(): List<Film> {
        return filmDao.allAlphabetically()
    }

    suspend fun getItemsLimitedToSize(size: Int): List<Film> {
        return filmDao.getItemBySize(size)
    }

    // TODO refactor code
    override suspend fun getFilmByUrl(stringUrl: String): Result<Film> = coroutineScope {
        val film = filmDao.getFilmByUrl(stringUrl)
        if (film == null) {
            val filmId = DbUtils.getLastPathFromUrl(stringUrl)
            val fetchedFilm = filmRemoteDataSource.getFilmById(filmId)
            if (fetchedFilm is Result.Success) fetchedFilm.data.let { filmDao.insert(it) }
            fetchedFilm
        } else {
            Result.Success(film)
        }
    }

    override suspend fun insertFilm(film: Film) = coroutineScope {
        filmDao.insert(film)
    }

    override fun getFilmsByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Film>?> {
        return when (predicate) {
            is GetAllAlphabetically -> filmDao.allAlphabeticallyAsFlow()
            is GetAllBySize -> filmDao.getItemBySizeAsFlow(predicate.size)
            else -> filmDao.allAsFlow()
        }
    }

    override fun allAsFlow(): Flow<List<Film>?> = filmDao.allAsFlow()

    override fun allAlphabeticallyAsFlow(): Flow<List<Film>?> = filmDao.allAlphabeticallyAsFlow()

    override fun getItemBySizeAsFlow(limit: Int): Flow<List<Film>?> = filmDao.getItemBySizeAsFlow(limit)

    override suspend fun fetchAndSync() = coroutineScope {
        val allFilms = filmRemoteDataSource.getAllFilms()
        println("fetch and sync all Films ==> $allFilms")
        if (allFilms is Result.Success) {
            allFilms.data.list?.let {
                println("TTTYYY Test ASYNC - total fetched file count: ${it.size}")
                if (it.size > 0) filmDao.clearTable()
                filmDao.insertList(it)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)
            }
        }
    }

    override suspend fun deleteAll(): Int = coroutineScope {
        filmDao.clearTable()
    }

}
