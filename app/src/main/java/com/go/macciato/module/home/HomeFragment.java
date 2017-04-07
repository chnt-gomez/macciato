package com.go.macciato.module.home;

import android.os.Bundle;

import com.go.macciato.R;
import com.go.macciato.core.BaseFragment;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeFragment extends BaseFragment{

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_LAYOUT, R.layout.fragment_home);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        super.init();

    }
}
