package com.marc0x71.mydagger1app.component;

import com.marc0x71.mydagger1app.presenter.LoginPresenter;

import dagger.Module;

/**
 * Created by MBollero on 03/03/2016.
 */
@Module(
        includes = {AppModule.class, RepositoryModule.class},
        injects = {LoginPresenter.class})
public class PresenterModule {
}
