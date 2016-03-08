package com.marc0x71.mydagger1app.component;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by marc0x71 on 08/03/2016.
 */
@Module(library = true)
public class ThreadModule {
    @Provides @Singleton @Named("observer")
    Scheduler provideObserverScheduler() { return Schedulers.newThread(); }

    @Provides @Singleton @Named("publisher")
    Scheduler providePublisherScheduler() { return AndroidSchedulers.mainThread(); }
}
