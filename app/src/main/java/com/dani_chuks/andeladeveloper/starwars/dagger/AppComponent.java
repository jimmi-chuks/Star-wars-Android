package com.dani_chuks.andeladeveloper.starwars.dagger;

import android.app.Application;

import com.dani_chuks.andeladeveloper.starwars.StarWarsApplication;
import com.dani_chuks.andeladeveloper.starwars.dagger.builder.ActivityBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class, NetworkModule.class})
public interface AppComponent {

    void inject(StarWarsApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
