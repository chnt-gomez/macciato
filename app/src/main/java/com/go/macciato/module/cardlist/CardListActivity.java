package com.go.macciato.module.cardlist;

import com.go.macciato.core.BaseActivity;

/**
 * Created by MAV1GA on 27/04/2017.
 */

public class CardListActivity extends BaseActivity implements CardListPresenterRequiredOps {

    @Override
    protected void init() {
        super.init();
        addFragment(CardListFragment.newInstance(), false);
    }
}
