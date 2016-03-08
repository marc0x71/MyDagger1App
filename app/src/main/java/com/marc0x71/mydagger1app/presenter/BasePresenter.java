package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.provider.IResourceProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    @Inject
    IResourceProvider resourceProvider;
    ;
    List<PendingUITask> pendingTasks = new ArrayList<>();
    private V view;

    @Override
    public void onViewAttached(V view) {
        this.view = view;
        checkPending();
    }

    protected void scheduleUITask(PendingUITask task) {
        if (view != null) {
            task.updateUI();
        } else {
            Timber.d("add to pending task...");
            pendingTasks.add(task);
        }
    }

    private void checkPending() {
        Timber.d("checkPending...");
        Observable.from(pendingTasks)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PendingUITask>() {
                    @Override
                    public void onCompleted() {
                        pendingTasks.clear();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PendingUITask task) {
                        Timber.d("updating view...");
                        task.updateUI();
                    }
                });
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    protected V getView() {
        return view;
    }

    protected interface PendingUITask {
        void updateUI();
    }
}
