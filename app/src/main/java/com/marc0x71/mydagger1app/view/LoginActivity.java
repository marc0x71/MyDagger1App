package com.marc0x71.mydagger1app.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.marc0x71.mydagger1app.MyApp;
import com.marc0x71.mydagger1app.R;
import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    LoginPresenter presenter;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initializePresenter();
    }

    private void initializePresenter() {
        presenter = new LoginPresenter(this);
        ((MyApp) getApplication()).getObjectGraph().inject(presenter);
        Timber.d("initializePresenter (%s)", presenter.toString());
    }

    @OnClick(R.id.sign_in_button)
    public void login() {
        Timber.d("login called with username '%s' and password '%s'", username.getText(), password.getText());
        presenter.userLogin(username.getText().toString(), password.getText().toString());
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
    }

}

