package com.dani_chuks.andeladeveloper.starwars.dagger

import io.reactivex.Scheduler

interface ISchedulerProvider {
    val ioScheduler: Scheduler
    val mainThreadScheduler: Scheduler
    val computationScheduler: Scheduler
}
