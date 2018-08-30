package com.dani_chuks.andeladeveloper.starwars.dagger;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public class SchedulerProvider implements ISchedulerProvider {

    @NonNull
    private final Scheduler mainThreadScheduler;
    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler computationScheduler;

    public SchedulerProvider(@NonNull final Scheduler mainThreadScheduler,
                             @NonNull final Scheduler ioScheduler,
                             @NonNull final Scheduler computationScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioScheduler = ioScheduler;
        this.computationScheduler = computationScheduler;
    }

    @NonNull
    @Override
    public Scheduler getIoScheduler() {
        return ioScheduler;
    }

    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return mainThreadScheduler;
    }

    @NonNull
    @Override
    public Scheduler getComputationScheduler() {
        return computationScheduler;
    }
}
