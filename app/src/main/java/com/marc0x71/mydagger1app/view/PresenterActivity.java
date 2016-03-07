package com.marc0x71.mydagger1app.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.marc0x71.mydagger1app.loader.PresenterLoader;
import com.marc0x71.mydagger1app.presenter.Presenter;

import timber.log.Timber;

/**
 * Created by marc0x71 on 07/03/2016.
 */
public abstract class PresenterActivity<P extends Presenter<V>, V> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P> {

    private static final int PRESENTER_LOADER_ID = 100;
    private P presenter;

    protected P getPresenter() {
        return presenter;
    }

    protected abstract PresenterLoader.Factory<P> getPresenterFactory();

    protected abstract void onPresenterReady(P presenter);

    protected abstract void onPresenterDestroyed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLoader();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initializeLoader();
    }

    private void initializeLoader() {
        Timber.d("initializeLoader");
        getSupportLoaderManager().initLoader(PRESENTER_LOADER_ID, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.d("onCreateLoader");
        return new PresenterLoader<>(getApplicationContext(), getPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        Timber.d("onLoadFinished");
        presenter = data;
        onPresenterReady(presenter);
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        Timber.d("onLoaderReset");
        presenter.onDestroyed();
        presenter = null;
        onPresenterDestroyed();
    }

    @Override
    protected void onStart() {
        Timber.d("onStart");
        super.onStart();
        if (presenter != null) presenter.onViewAttached((V) this);
    }

    @Override
    protected void onStop() {
        Timber.d("onStop");
        super.onStop();
        if (presenter != null) presenter.onViewDetached();
    }
}
