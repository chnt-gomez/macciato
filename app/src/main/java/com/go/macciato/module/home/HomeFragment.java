package com.go.macciato.module.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.go.macciato.R;
import com.go.macciato.core.BaseFragment;

import butterknife.BindView;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.cards_list)
    RecyclerView mRecyclerView;

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
