package com.dani_chuks.andeladeveloper.starwars.di.builder

import com.dani_chuks.andeladeveloper.starwars.home.HomeActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Module
abstract class ActivityBuilder {

//    @ContributesAndroidInjector(modules = [HomeModule::class])
    @ContributesAndroidInjector()
    internal abstract fun bindHomeActivity(): HomeActivity
}
