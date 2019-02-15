package com.dani_chuks.andeladeveloper.starwars.dagger.qualifiers

import javax.inject.Qualifier

@Qualifier
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ComputationScheduler
