package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Starship
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StarshipRepository @Inject
constructor(private val apiService: ApiService,
            private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Starship> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Starship>>
        get() {
            fetchIfEmpty()
            return appDatabase.starshipDao().all
        }

    override val allAlphabetically: Flowable<List<Starship>>
        get() {
            fetchIfEmpty()
            return appDatabase.starshipDao().allAlphabetically
        }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Starship>> {
        fetchIfEmpty()
        return appDatabase.starshipDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Starship> {
        val starshipId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getStarshipById(starshipId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { starship -> appDatabase.starshipDao().insertStarship(starship) })

        return appDatabase.starshipDao().getStarshipByURL(stringUrl)
    }

    override fun insertItem(starship: Starship) {
        disposableManager.add(Observable.just(starship)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.starshipDao().insertStarship(starship) })
    }

    override fun insertItemList(starship: List<Starship>) {
        disposableManager.add(Observable.just(starship)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.starshipDao().insertStarshipList(starship) })
    }

    private fun fetchIfEmpty() {
        val firstPage = 1
        disposableManager.add(
                apiService.getStarshipList(firstPage)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.starship?.let { appDatabase.starshipDao().insertStarshipList(it) }
                            preferenceManager.setResourceNextPage(AppConstants.STARSHIP_RESOURCE_NAME, firstPage + 1)
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.STARSHIP_RESOURCE_NAME)
                        })
    }
}
