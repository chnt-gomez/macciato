package com.go.macciato.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.go.macciato.R;
import com.go.macciato.module.home.HomeFragment;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
    }

    protected void init() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, HomeFragment.newInstance(), null).commit();
    }


}
