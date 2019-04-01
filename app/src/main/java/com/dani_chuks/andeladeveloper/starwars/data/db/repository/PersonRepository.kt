package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.Result
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.models.People
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PersonRepository @Inject
constructor(private val appDatabase: AppDatabase,
            val remoteDataSource: PersonRemoteDataSource,
            private val preferenceManager: SharedPreferenceManager) {

    suspend fun all(): List<Person> {
            return appDatabase.personDao().all()
        }

    suspend fun allAlphabetically(): List<Person>  {
            return appDatabase.personDao().allAlphabetically()
        }

    suspend fun getItemsLimitedToSize(size: Int): List<Person> {
        return appDatabase.personDao().getItemBySize(size)
    }

    suspend fun getPersonById(stringUrl: String): Person = coroutineScope {
        val person = async { appDatabase.personDao().getPersonByURL(stringUrl) }.await()
        if(person == null) {
            val personId = DbUtils.getLastPathFromUrl(stringUrl)
            val personFromRemote =  remoteDataSource.getPersonById(personId)
            if(personFromRemote is Result.Success) personFromRemote.data.let { appDatabase.personDao().insert(it) }
        }
       person
    }

    suspend fun insertPerson(person: Person)= coroutineScope {
        appDatabase.personDao().insert(person)
    }

    suspend fun insertItemList(people: List<Person>) = coroutineScope {
        appDatabase.personDao().insertList(people)
    }

    suspend fun fetchAndSync(page: Int): Result<People> = coroutineScope {
        val peopleFetched = remoteDataSource.getPeopleFromPage(page)
        println("fetch and sync People from page $page ==> $peopleFetched")
        if (peopleFetched is Result.Success) {
            peopleFetched.data.person?.let {
                appDatabase.personDao().insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.PERSON_RESOURCE_NAME, page + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME)
            }
        }
        peopleFetched
    }
}
