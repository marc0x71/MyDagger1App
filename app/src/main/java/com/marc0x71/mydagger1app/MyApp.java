package com.marc0x71.mydagger1app;

import android.app.Application;
import android.util.Log;

import com.marc0x71.mydagger1app.component.AppModule;
import com.marc0x71.mydagger1app.component.PresenterModule;
import com.marc0x71.mydagger1app.component.RepositoryModule;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public class MyApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new ReleaseTree());
        }

        objectGraph = ObjectGraph.create(new AppModule(this), new RepositoryModule(), new PresenterModule());
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    private static class ReleaseTree extends Timber.Tree {
        private static final int MAX_LOG_LENGTH = 4000;

        @Override
        protected boolean isLoggable(int priority) {
            return !(priority == Log.VERBOSE || priority == Log.DEBUG);
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (!isLoggable(priority)) return;

            if (priority == Log.ASSERT) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }
            return;

        }
    }
}
