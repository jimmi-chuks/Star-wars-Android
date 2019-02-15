package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PersonRepository @Inject
constructor(private val apiService: ApiService,
            private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Person> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Person>>
        get() {
            fetchIfEmpty()
            return appDatabase.personDao().all
        }

    override val allAlphabetically: Flowable<List<Person>>
        get() {
            fetchIfEmpty()
            return appDatabase.personDao().allAlphabetically
        }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Person>> {
        fetchIfEmpty()
        return appDatabase.personDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Person> {
        val personId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getPersonById(personId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { person -> appDatabase.personDao().insertPerson(person) })

        return appDatabase.personDao().getPersonByURL(stringUrl)
    }

    override fun insertItem(person: Person) {
        disposableManager.add(
                Observable.just(person)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { appDatabase.personDao().insertPerson(person) })

    }

    override fun insertItemList(people: List<Person>) {
        disposableManager.add(Observable.just(people)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.personDao().insertPeople(people) })
    }

    private fun fetchIfEmpty() {
        val firstPage = 1
        disposableManager.add(
                apiService.getPeople(firstPage)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.person?.let { appDatabase.personDao().insertPeople(it) }
                            preferenceManager.setResourceNextPage(AppConstants.PERSON_RESOURCE_NAME, firstPage + 1)
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.PERSON_RESOURCE_NAME)
                        })
    }
}
