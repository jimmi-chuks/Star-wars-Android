package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person

@Dao
abstract class PersonDao: BaseDao<Person> {
    @Query("SELECT * FROM person")
    abstract suspend fun  all(): List<Person>

    @Query("SELECT * FROM person ORDER BY name ASC")
    abstract suspend fun allAlphabetically(): List<Person>

    @Query("SELECT * FROM person LIMIT :limit")
    abstract suspend fun getItemBySize(limit: Int): List<Person>

    @Query("SELECT * FROM person WHERE gender = :gender")
    abstract suspend fun getPeopleByGender(gender: String): List<Person>

    @Query("SELECT * FROM person WHERE url = :personUrl")
    abstract suspend fun getPersonByURL(personUrl: String): Person?
}
