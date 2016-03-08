package com.marc0x71.mydagger1app.contract;

/**
 * Created by marc0x71 on 08/03/2016.
 */
public interface HelloContract {

    public interface View {
        void sayHello();
    }

    public interface Presenter {
        void onButtonPressed();
    }

}
