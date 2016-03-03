package com.marc0x71.mydagger1app.component;

import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.model.UserModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MBollero on 03/03/2016.
 */
@Module(library = true)
public class RepositoryModule {
    @Provides
    @Singleton
    LoginContract.Model provideLoginModel() {
        return new UserModel();
    }
}
