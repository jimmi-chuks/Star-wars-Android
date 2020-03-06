package com.dani_chuks.andeladeveloper.starwars.data.db.repository.people

import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.safeApiCall
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import com.dani_chuks.andeladeveloper.starwars.di.Result
import java.io.IOException
import javax.inject.Inject

class PersonRemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getPeopleFromPage(page: Int) = safeApiCall(
            call = { requestPeople(page) },
            errorMessage = "Error getting People In Page"
    )

    suspend fun getPersonById(id: Int) = safeApiCall(
            call = { requestPersonById(id)},
            errorMessage = "Error getting person by Id"
    )

    private suspend fun requestPeople(page: Int): Result<EntityList<Person>> {
        val response = service.getPeople(page)
        if(response.isSuccessful) {
            val body = response.body()
            if(body != null) return  Result.Success(body)
        }
        return Result.Error(
                IOException("Error Fetching People ${response.code()} ${response.message()}")
        )
    }

    private suspend fun requestPersonById(personId: Int): Result<Person> {
        val personResponse = service.getPersonById(personId)
        if (personResponse.isSuccessful) {
            val personBody = personResponse.body()
            if (personBody != null) return Result.Success(personBody)
        }
        return Result.Error(
                IOException("Error getting person for Id $personId ${personResponse.code()} ${personResponse.message()}")
        )
    }
}
