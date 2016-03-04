package com.marc0x71.mydagger1app.component;

import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.provider.IResourceProvider;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

/**
 * Created by marc0x71 on 03/03/2016.
 */
@Module(library = true, injects = {LoginPresenter.class})
public class MockTestModule {
    @Provides
    IResourceProvider provideResourceProvider() {
        return Mockito.mock(IResourceProvider.class);
    }

    @Provides
    LoginContract.Model provideLoginModel() {
        return Mockito.mock(LoginContract.Model.class);
    }
}
