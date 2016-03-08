package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.contract.HelloContract;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by marc0x71 on 08/03/2016.
 */
public class HelloPresenter extends BasePresenter<HelloContract.View> implements HelloContract.Presenter {

    @Override
    public void onButtonPressed() {
        Observable.just(null)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(observerScheduler)
                .observeOn(publisherScheduler)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        scheduleUITask(new PendingUITask() {
                            @Override
                            public void updateUI() {
                                getView().sayHello();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Object o) {
                    }
                });
    }

    @Override
    public void onDestroyed() {

    }
}
