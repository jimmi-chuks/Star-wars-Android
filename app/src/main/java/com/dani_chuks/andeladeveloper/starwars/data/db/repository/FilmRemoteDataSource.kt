package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.FilmList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import com.dani_chuks.andeladeveloper.starwars.di.Result
import java.io.IOException
import javax.inject.Inject

class FilmRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getAllFilms() = safeApiCall(
            call = { requestAllFilms() },
            errorMessage = "Error getting all Films"
    )

    suspend fun getFilmById(id: Int) = safeApiCall(
            call = { requestFilmById(id) },
            errorMessage = "Error getting Film with ID"
    )

    private suspend fun requestAllFilms(): Result<FilmList> {
        val response = service.allFilms.await()
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error getting all films ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestFilmById(filmId: Int): Result<Film> {
        val filmResponse = service.getFilmById(filmId).await()
        if (filmResponse.isSuccessful) {
            val filmBody = filmResponse.body()
            if (filmBody != null) return Result.Success(filmBody)
        }
        return Result.Error(
                IOException("Error getting film for Id $filmId ${filmResponse.code()} ${filmResponse.message()}")
        )
    }
}
