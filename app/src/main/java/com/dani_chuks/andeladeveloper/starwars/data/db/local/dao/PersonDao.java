package com.dani_chuks.andeladeveloper.starwars.data.db.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    Flowable<List<Person>> getAll();

    @Query("SELECT * FROM person LIMIT :limit")
    Flowable<List<Person>> getItemBySize(int limit);

    @Query("SELECT * FROM person ORDER BY name ASC" )
    Flowable<List<Person>> getAllAlphabetically();

    @Query("SELECT * FROM person WHERE gender = :gender")
    Flowable <List<Person>> getPeopleByGender(String gender);

    @Query("SELECT * FROM person WHERE url = :personUrl")
    Flowable<Person> getPersonByURL(String personUrl);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePeople(List<Person> people);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPeople(List<Person> people);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPerson(Person person);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePerson(Person person);
}
