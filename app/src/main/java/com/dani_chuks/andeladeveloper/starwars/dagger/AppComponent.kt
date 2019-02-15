package com.dani_chuks.andeladeveloper.starwars.dagger

import android.app.Application
import com.dani_chuks.andeladeveloper.starwars.StarWarsApplication
import com.dani_chuks.andeladeveloper.starwars.dagger.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, NetworkModule::class))
interface AppComponent {

    fun inject(app: StarWarsApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
