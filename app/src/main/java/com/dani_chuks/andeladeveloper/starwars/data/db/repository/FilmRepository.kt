package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.FilmList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FilmRepository @Inject constructor(val appDatabase: AppDatabase,
                                         val filmRemoteDataSource: FilmRemoteDataSource,
                                         private val preferenceManager: SharedPreferenceManager) {


    suspend fun all(): List<Film> {
        return appDatabase.filmDao().all()
    }

    suspend fun allAlphabetically(): List<Film> {
        return appDatabase.filmDao().allAlphabetically()
    }

    suspend fun getItemsLimitedToSize(size: Int): List<Film> {
        return appDatabase.filmDao().getItemBySize(size)
    }

    // TODO refactor code
    suspend fun getItemByUrl(stringUrl: String): Result<Film> = coroutineScope {
        val film = async { appDatabase.filmDao().getFilmByUrl(stringUrl)}.await()
        if(film == null){
            val filmId = DbUtils.getLastPathFromUrl(stringUrl)
            val fetchedFilm = filmRemoteDataSource.getFilmById(filmId)
            if (fetchedFilm is Result.Success) fetchedFilm.data.let { appDatabase.filmDao().insert(it) }
            fetchedFilm
        } else {
            Result.Success(film)
        }
    }

    private suspend fun insertItem(film: Film) = coroutineScope {
        appDatabase.filmDao().insert(film)
    }

    suspend fun insertItemList(t: List<Film>) = coroutineScope {
        appDatabase.filmDao().insertList(t)
    }

    suspend fun fetchAndSync(): Result<FilmList> = coroutineScope {
        val allFilms = filmRemoteDataSource.getAllFilms()
        println("fetch and sync all Films ==> $allFilms")
        if (allFilms is Result.Success) {
            allFilms.data.film?.let {
                println("TTTYYY Test ASYNC - total fetched file count: ${it.size}")
                appDatabase.filmDao().insertList(it)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)
            }
        }
        allFilms
    }

    suspend fun deleteAll(): Int = coroutineScope {
        appDatabase.filmDao().clearTable()
    }

}
