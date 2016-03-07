package com.marc0x71.mydagger1app.presenter;

/**
 * Created by marc0x71 on 07/03/2016.
 */
public interface Presenter<V> {
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
