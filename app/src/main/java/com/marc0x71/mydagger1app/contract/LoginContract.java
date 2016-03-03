package com.marc0x71.mydagger1app.contract;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public interface LoginContract {

    interface Model {
        boolean validate(String username, String password);
    }

    interface View {
        void showInfoMessage(String message);

        void showErrorMessage(String message);

        void goToMainActivity();
    }

    interface Action {
        void userLogin(String username, String password);
    }
}
