package com.dani_chuks.andeladeveloper.starwars

import android.app.Activity
import android.app.Application
import com.dani_chuks.andeladeveloper.starwars.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class StarWarsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        //        Fabric.with(this, new Crashlytics());
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}