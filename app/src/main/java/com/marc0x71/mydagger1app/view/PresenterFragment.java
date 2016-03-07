package com.marc0x71.mydagger1app.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.marc0x71.mydagger1app.loader.PresenterLoader;
import com.marc0x71.mydagger1app.presenter.Presenter;

import timber.log.Timber;

/**
 * Created by marc0x71 on 07/03/2016.
 */
public abstract class PresenterFragment<P extends Presenter<V>, V> extends Fragment implements
        LoaderManager.LoaderCallbacks<P> {

    private static final int PRESENTER_LOADER_ID = 100;
    private P presenter;

    protected P getPresenter() {
        return presenter;
    }

    protected abstract PresenterLoader.Factory<P> getPresenterFactory();

    protected abstract void onPresenterReady(P presenter);

    protected abstract void onPresenterDestroyed();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("onActivityCreated");
        getLoaderManager().initLoader(PRESENTER_LOADER_ID, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        Timber.d("onCreateLoader");
        return new PresenterLoader<>(getActivity().getApplicationContext(), getPresenterFactory());
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
    public void onResume() {
        Timber.d("onResume");
        super.onResume();
        if (presenter != null) presenter.onViewAttached((V) this);
    }

    @Override
    public void onPause() {
        Timber.d("onPause");
        super.onPause();
        if (presenter != null) presenter.onViewDetached();
    }
}
