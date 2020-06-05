package com.dani_chuks.andeladeveloper.starwars.data.db.repository.people

import com.dani_chuks.andeladeveloper.starwars.data.db.repository.DaoPredicate
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    suspend fun getPeopleByPredicate(predicate: DaoPredicate): List<Person>

    suspend fun fetchAndSync(page: Int)

    suspend fun getPersonById(stringUrl: String): Result<Person>

    suspend fun insertPerson(person: Person)

    fun getPeopleByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Person>?>

    fun allAsFlow(): Flow<List<Person>?>

    fun allAlphabeticallyAsFlow(): Flow<List<Person>?>

    fun getItemBySizeAsFlow(limit: Int): Flow<List<Person>?>

    fun getPeopleByGenderAsFlow(gender: String): Flow<List<Person>?>

    fun getPersonByURLAsFlow(personUrl: String): Flow<Person?>
}