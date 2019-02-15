package com.dani_chuks.andeladeveloper.starwars.data.db.repository

import com.dani_chuks.andeladeveloper.starwars.dagger.ISchedulerProvider
import com.dani_chuks.andeladeveloper.starwars.data.AppConstants
import com.dani_chuks.andeladeveloper.starwars.data.SharedPreferenceManager
import com.dani_chuks.andeladeveloper.starwars.data.db.DataSource
import com.dani_chuks.andeladeveloper.starwars.data.db.DbUtils
import com.dani_chuks.andeladeveloper.starwars.data.db.local.AppDatabase
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FilmRepository @Inject constructor(private val apiService: ApiService, private val appDatabase: AppDatabase,
            private val schedulerProvider: ISchedulerProvider,
            private val preferenceManager: SharedPreferenceManager) : DataSource<Film> {
    private val disposableManager = CompositeDisposable()

    override val all: Flowable<List<Film>>
        get() {
            fetchIfEmpty()
            return appDatabase.filmDao().all
        }

    override val allAlphabetically: Flowable<List<Film>>
        get() {
            fetchIfEmpty()
            return appDatabase.filmDao().allAlphabetically
        }

    fun getFilmByUrl(filmUrl: String): Flowable<Film> {
        val url = DbUtils.getLastPathFromUrl(filmUrl)
        disposableManager.add(
                apiService.getFilmById(url)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { this.insertItem(it) })

        return appDatabase.filmDao().getFilmByUrl(filmUrl)
    }

    override fun getItemsLimitedToSize(size: Int): Flowable<List<Film>> {
        fetchIfEmpty()
        return appDatabase.filmDao().getItemBySize(size)
    }

    override fun getItemByUrl(stringUrl: String): Flowable<Film> {
        val filmId = DbUtils.getLastPathFromUrl(stringUrl)
        disposableManager.add(
                apiService.getFilmById(filmId)
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { appDatabase.filmDao().insertFilm(it) })

        return appDatabase.filmDao().getFilmByUrlAsSingle(stringUrl)
    }

    override fun insertItem(film: Film) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { time -> appDatabase.filmDao().insertFilm(film) })
    }

    override fun insertItemList(t: List<Film>) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.ioScheduler)
                .subscribe { appDatabase.filmDao().insertFilms(t) })
    }

    private fun fetchIfEmpty() {
        disposableManager.add(
                apiService.allFilms
                        .subscribeOn(schedulerProvider.ioScheduler)
                        .subscribe { response ->
                            response.film?.let { appDatabase.filmDao().insertFilms(it) }
                            preferenceManager.setDataTypeFetchedOnce(AppConstants.FILM_RESOURCE_NAME)
                        })
    }

}
