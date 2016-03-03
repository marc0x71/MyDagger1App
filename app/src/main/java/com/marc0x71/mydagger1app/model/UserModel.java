package com.marc0x71.mydagger1app.model;

import com.marc0x71.mydagger1app.contract.LoginContract;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public class UserModel implements LoginContract.Model {


    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public boolean validate(String username, String password) {
        return username.equalsIgnoreCase(USERNAME) && username.compareTo(PASSWORD) == 0;
    }
}
