package com.go.macciato.module.cardlist;

import android.content.Context;
import android.os.Bundle;

import com.go.macciato.R;
import com.go.macciato.core.BaseFragment;

/**
 * Created by MAV1GA on 27/04/2017.
 */

public class CardListFragment extends BaseFragment implements CardListViewRequiredOps{

    private CardListPresenterRequiredOps cListPresenter;

    public static CardListFragment newInstance(){
        CardListFragment fragment = new CardListFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_LAYOUT, R.layout.fragment_card_list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cListPresenter = (CardListPresenterRequiredOps)context;
    }

}
