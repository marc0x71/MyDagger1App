package com.marc0x71.mydagger1app.component;

import com.marc0x71.mydagger1app.presenter.HelloPresenter;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;

import dagger.Module;

/**
 * Created by marc0x71 on 03/03/2016.
 */
@Module(
        includes = {AppModule.class, RepositoryModule.class, ThreadModule.class},
        injects = {LoginPresenter.class, HelloPresenter.class})
public class PresenterModule {
}
