package com.dani_chuks.andeladeveloper.starwars.data.db.repository.people

import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.repository.*
import com.dani_chuks.andeladeveloper.starwars.data.models.EntityList
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import com.dani_chuks.andeladeveloper.starwars.di.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject
constructor(private val appDatabase: AppDatabase,
            val remoteDataSource: PersonRemoteDataSource,
            private val preferenceManager: SharedPreferenceManager): PeopleRepository {

    val peopleDao = appDatabase.personDao()

    override suspend fun getPeopleByPredicate(predicate: DaoPredicate): List<Person> {
        return when(predicate){
            is GetAllAlphabeticallyBySize -> TODO()
            is GetAllBySize -> peopleDao.getItemBySize(predicate.size)
            GetAll -> peopleDao.all()
            GetAllAlphabetically -> peopleDao.allAlphabetically()
        }
    }

    override suspend fun getPersonById(stringUrl: String): Result<Person> = coroutineScope {
        val person = peopleDao.getPersonByURL(stringUrl)
        if (person == null) {
            val personId = DbUtils.getLastPathFromUrl(stringUrl)
            val personFromRemote = remoteDataSource.getPersonById(personId)
            if (personFromRemote is Result.Success) personFromRemote.data.let { peopleDao.insert(it) }
            personFromRemote
        } else {
            Result.Success(person)
        }
    }

    override suspend fun insertPerson(person: Person) = coroutineScope {
        peopleDao.insert(person)
    }

    override fun getPeopleByPredicateAsFlow(predicate: DaoPredicate): Flow<List<Person>?> {
        return when(predicate){
            is GetAllAlphabeticallyBySize -> TODO()
            is GetAllBySize -> peopleDao.getItemBySizeAsFlow(predicate.size)
            GetAll -> peopleDao.allAsFlow()
            GetAllAlphabetically -> peopleDao.allAlphabeticallyAsFlow()
        }
    }

    override fun allAsFlow(): Flow<List<Person>?> = peopleDao.allAsFlow()

    override fun allAlphabeticallyAsFlow(): Flow<List<Person>?> = peopleDao.allAlphabeticallyAsFlow()

    override fun getItemBySizeAsFlow(limit: Int): Flow<List<Person>?> = peopleDao.getItemBySizeAsFlow(limit)

    override fun getPeopleByGenderAsFlow(gender: String): Flow<List<Person>?> =
            peopleDao.getPeopleByGenderAsFlow(gender)

    override fun getPersonByURLAsFlow(personUrl: String): Flow<Person?> =
            peopleDao.getPersonByURLAsFlow(personUrl)

    suspend fun insertPeople(people: List<Person>) = coroutineScope {
        peopleDao.insertList(people)
    }

    override suspend fun fetchAndSync(page: Int) = coroutineScope {
        val peopleFetched = remoteDataSource.getPeopleFromPage(page)
        if (peopleFetched is Result.Success) {
            peopleFetched.data.list?.let {
                peopleDao.insertList(it)
                preferenceManager.setResourceNextPage(AppConstants.PERSON_RESOURCE_NAME, page + 1)
                preferenceManager.setDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME)
            }
        }
    }
}
