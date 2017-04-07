package com.go.macciato.core;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class BaseFragment extends Fragment {

    protected static final String FRAGMENT_LAYOUT = "fragment_layout";
    protected View fragmentView;

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
}
