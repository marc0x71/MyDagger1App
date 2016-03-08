package com.marc0x71.mydagger1app.mock.component;

import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.presenter.BasePresenterTest;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;
import com.marc0x71.mydagger1app.provider.IResourceProvider;

import org.mockito.Mockito;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by marc0x71 on 03/03/2016.
 */
@Module(library = true, injects = {LoginPresenter.class, BasePresenterTest.MyPresenter.class})
public class MockTestModule {
    @Provides
    IResourceProvider provideResourceProvider() {
        return Mockito.mock(IResourceProvider.class);
    }

    @Provides
    LoginContract.Model provideLoginModel() {
        return Mockito.mock(LoginContract.Model.class);
    }

    @Provides
    @Singleton
    @Named("observer")
    Scheduler provideObserverScheduler() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named("publisher")
    Scheduler providePublisherScheduler() {
        return Schedulers.immediate();
    }

}
