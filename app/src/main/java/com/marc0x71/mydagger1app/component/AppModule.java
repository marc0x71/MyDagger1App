package com.marc0x71.mydagger1app.component;

import android.content.Context;

import com.marc0x71.mydagger1app.provider.IResourceProvider;
import com.marc0x71.mydagger1app.provider.ResourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MBollero on 03/03/2016.
 */
@Module(library = true)
public class AppModule {
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    IResourceProvider provideResourceProvider() {
        return new ResourceProvider(context);
    }

}
