package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.R;
import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.provider.IResourceProvider;

import javax.inject.Inject;

/**
 * Created by MBollero on 03/03/2016.
 */
public class LoginPresenter implements LoginContract.Action {

    @Inject
    LoginContract.Model model;
    @Inject
    IResourceProvider resourceProvider;

    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void userLogin(String username, String password) {
        if (model.validate(username, password)) {
            view.showInfoMessage(resourceProvider.getString(R.string.login_success_message));
            view.goToMainActivity();
        } else {
            view.showErrorMessage(resourceProvider.getString(R.string.login_failure_message));
        }
    }

    public void dump() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "LoginPresenter{" +
                "model=" + model +
                ", resourceProvider=" + resourceProvider +
                ", view=" + view +
                '}';
    }
}
