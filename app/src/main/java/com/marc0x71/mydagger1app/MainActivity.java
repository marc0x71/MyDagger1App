package com.marc0x71.mydagger1app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializePresenter();
    }

    private void initializePresenter() {
        presenter = new LoginPresenter(this);
        MyApp app = (MyApp) getApplication();
        app.getObjectGraph().inject(presenter);
        System.out.println(app.getObjectGraph().toString());
        presenter.dump();
    }

    @Override
    public void showInfoMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void goToMainActivity() {

    }
}
