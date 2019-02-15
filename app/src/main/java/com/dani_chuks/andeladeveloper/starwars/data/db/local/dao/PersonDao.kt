package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao

import androidx.room.*
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import io.reactivex.Flowable

@Dao
interface PersonDao {
    @get:Query("SELECT * FROM person")
    val all: Flowable<List<Person>>

    @get:Query("SELECT * FROM person ORDER BY name ASC")
    val allAlphabetically: Flowable<List<Person>>

    @Query("SELECT * FROM person LIMIT :limit")
    fun getItemBySize(limit: Int): Flowable<List<Person>>

    @Query("SELECT * FROM person WHERE gender = :gender")
    fun getPeopleByGender(gender: String): Flowable<List<Person>>

    @Query("SELECT * FROM person WHERE url = :personUrl")
    fun getPersonByURL(personUrl: String): Flowable<Person>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePeople(people: List<Person>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPeople(people: List<Person>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(person: Person)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePerson(person: Person)
}
