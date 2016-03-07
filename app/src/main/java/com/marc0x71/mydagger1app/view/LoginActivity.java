package com.marc0x71.mydagger1app.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.marc0x71.mydagger1app.MyApp;
import com.marc0x71.mydagger1app.R;
import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.loader.PresenterLoader;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends PresenterActivity<LoginPresenter, LoginContract.View> implements LoginContract.View {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_button)
    public void login() {
        Timber.d("login called with username '%s' and password '%s'", username.getText(), password.getText());
        getPresenter().userLogin(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showInfoMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainActivity() {
        Timber.i("goToMainActivity() called!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected PresenterLoader.Factory<LoginPresenter> getPresenterFactory() {
        return new PresenterLoader.Factory<LoginPresenter>() {
            @Override
            public LoginPresenter create() {
                LoginPresenter presenter = new LoginPresenter();
                ((MyApp) getApplication()).getObjectGraph().inject(presenter);
                Timber.d("initializePresenter (%s)", presenter.toString());
                return presenter;
            }
        };
    }

    @Override
    protected void onPresenterReady(LoginPresenter presenter) {

    }

    @Override
    protected void onPresenterDestroyed() {

    }
}

