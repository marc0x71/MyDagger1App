package com.marc0x71.mydagger1app.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.marc0x71.mydagger1app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by marc0x71 on 04/03/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.hello)
    TextView hello_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
