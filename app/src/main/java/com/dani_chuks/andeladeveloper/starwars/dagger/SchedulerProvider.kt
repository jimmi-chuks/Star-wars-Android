package com.dani_chuks.andeladeveloper.starwars.dagger

import io.reactivex.Scheduler
import javax.inject.Inject

class SchedulerProvider @Inject constructor(override val mainThreadScheduler: Scheduler,
                                            override val ioScheduler: Scheduler,
                                            override val computationScheduler: Scheduler) : ISchedulerProvider
