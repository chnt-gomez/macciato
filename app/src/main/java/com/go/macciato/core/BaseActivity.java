package com.go.macciato.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.go.macciato.R;
import com.go.macciato.data.MacciatoEngine;
import com.go.macciato.data.ModelOps;
import com.go.macciato.module.home.HomeFragment;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class BaseActivity extends AppCompatActivity implements RequiredPresenterOps, PresenterOps{

    protected RequiredViewOps view;
    protected ModelOps model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
    }

    protected void init() {
        if (model == null){
            model = MacciatoEngine.getInstance(this);
        }
    }

    protected void addFragment(BaseFragment fragment, boolean addToBackStack){
        if (addToBackStack){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment)
                    .addToBackStack(null).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (model != null){
            model.onConfigurationChanged(this);
        }
    }

    @Override
    public void setViewLayer(RequiredViewOps fragment) {
        view = fragment;
    }

    @Override
    public void onOperationError(int stringRes) {
        view.onOperationError(getString(stringRes));
    }

    @Override
    public void onOperationSuccessful(Long operationId) {
        view.onOperationSuccessful(getString(R.string.operation_successful));
    }
}
