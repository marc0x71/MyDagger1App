package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.provider.IResourceProvider;

import javax.inject.Inject;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    @Inject
    IResourceProvider resourceProvider;

}
