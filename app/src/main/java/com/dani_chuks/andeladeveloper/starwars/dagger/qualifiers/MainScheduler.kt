package com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Qualifier

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class MainScheduler
