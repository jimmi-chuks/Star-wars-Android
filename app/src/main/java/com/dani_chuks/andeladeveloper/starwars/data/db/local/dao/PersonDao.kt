package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PersonDao: BaseDao<Person> {
    @Query("SELECT * FROM person")
    abstract suspend fun all(): List<Person>

    @Query("SELECT * FROM person ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Person>

    @Query("SELECT * FROM person LIMIT :limit")
    abstract suspend fun getItemBySize(limit: Int): List<Person>

    @Query("SELECT * FROM person WHERE gender = :gender")
    abstract suspend fun getPeopleByGender(gender: String): List<Person>

    @Query("SELECT * FROM person WHERE url = :personUrl")
    abstract suspend fun getPersonByURL(personUrl: String): Person?

    @Query("SELECT * FROM person")
    abstract fun allAsFlow(): Flow<List<Person>?>

    @Query("SELECT * FROM person ORDER BY name ASC")
    abstract fun allAlphabeticallyAsFlow(): Flow<List<Person>?>

    @Query("SELECT * FROM person LIMIT :limit")
    abstract fun getItemBySizeAsFlow(limit: Int): Flow<List<Person>?>

    @Query("SELECT * FROM person WHERE gender = :gender")
    abstract fun getPeopleByGenderAsFlow(gender: String): Flow<List<Person>?>

    @Query("SELECT * FROM person WHERE url = :personUrl")
    abstract fun getPersonByURLAsFlow(personUrl: String): Flow<Person?>
}
