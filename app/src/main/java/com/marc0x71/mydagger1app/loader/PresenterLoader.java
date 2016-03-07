package com.marc0x71.mydagger1app.loader;

import android.content.Context;
import android.support.v4.content.Loader;

import com.marc0x71.mydagger1app.presenter.Presenter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by marc0x71 on 07/03/2016.
 */
public class PresenterLoader<T extends Presenter> extends Loader<T> {

    private Factory<T> factory = null;
    private T presenter = null;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public PresenterLoader(Context context, @NotNull Factory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter.onDestroyed();
        presenter = null;
    }

    public interface Factory<T> {
        T create();
    }
}
