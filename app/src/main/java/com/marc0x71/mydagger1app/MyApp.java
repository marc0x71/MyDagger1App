package com.marc0x71.mydagger1app;

import android.app.Application;

import com.marc0x71.mydagger1app.component.AppModule;
import com.marc0x71.mydagger1app.component.PresenterModule;
import com.marc0x71.mydagger1app.component.RepositoryModule;

import dagger.ObjectGraph;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public class MyApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new AppModule(this), new RepositoryModule(), new PresenterModule());
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
