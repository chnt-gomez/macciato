package com.go.macciato.core;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class BaseFragment extends Fragment implements RequiredViewOps{

    protected static final String FRAGMENT_LAYOUT = "fragment_layout";
    protected View fragmentView;

    protected PresenterOps presenter;

    protected View findView(int resId){
        return fragmentView.findViewById(resId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(getArguments().getInt(FRAGMENT_LAYOUT), null);
        init();
        return fragmentView;
    }

    protected void init(){
        if (fragmentView != null){
            ButterKnife.bind(this, fragmentView);
        }
    }

    protected void showSnackBar(String message){
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setViewLayer(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = (PresenterOps)context;
    }

    @Override
    public void onOperationSuccessful(String message) {
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOperationSuccessful(String message, long operationId) {
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOperationError(String message) {
        Snackbar snack = Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT);
        TextView tv = (TextView)snack.getView().findViewById((android.support.design.R.id.snackbar_text));
        tv.setTextColor(Color.parseColor("#ff7f7f"));
        snack.show();
    }
}
