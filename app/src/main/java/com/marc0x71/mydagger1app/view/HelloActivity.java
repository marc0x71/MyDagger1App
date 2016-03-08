package com.marc0x71.mydagger1app.view;

import android.os.Bundle;
import android.widget.TextView;

import com.marc0x71.mydagger1app.MyApp;
import com.marc0x71.mydagger1app.R;
import com.marc0x71.mydagger1app.contract.HelloContract;
import com.marc0x71.mydagger1app.loader.PresenterLoader;
import com.marc0x71.mydagger1app.presenter.HelloPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class HelloActivity extends PresenterActivity<HelloPresenter, HelloContract.View> implements HelloContract.View {

    @Inject
    HelloPresenter presenter;

    @Bind(R.id.textView)
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonPressed() {
        Timber.d("Button pressed!");
        message.setText("");
        getPresenter().onButtonPressed();
    }

    @Override
    protected PresenterLoader.Factory<HelloPresenter> getPresenterFactory() {
        return new PresenterLoader.Factory<HelloPresenter>() {
            @Override
            public HelloPresenter create() {
                HelloPresenter presenter = new HelloPresenter();
                ((MyApp) getApplication()).getObjectGraph().inject(presenter);
                Timber.d("initializePresenter (%s)", presenter.toString());
                return presenter;
            }
        };
    }

    @Override
    protected void onPresenterReady(HelloPresenter presenter) {
    }

    @Override
    protected void onPresenterDestroyed() {

    }

    @Override
    public void sayHello() {
        Timber.d("sayHello!");
        message.setText("Hello!");
    }
}
