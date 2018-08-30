package com.dani_chuks.andeladeveloper.starwars.dagger;

import io.reactivex.Scheduler;

public interface ISchedulerProvider {
    Scheduler getIoScheduler();
    Scheduler getMainThreadScheduler();
    Scheduler getComputationScheduler();
}
