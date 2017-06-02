package com.go.macciato.module.config;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.go.macciato.core.BaseActivity;
import com.go.macciato.model.CreditCard;

/**
 * Created by MAV1GA on 20/04/2017.
 */

public class CardConfigActivity extends BaseActivity implements CardConfigPresenterRequiredOps {

    private long cardId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardId = getIntent().getLongExtra("card_id", -1);
        init();
    }

    @Override
    protected void init() {
        super.init();
        addFragment(CardConfigFragment.newInstance(), false);
    }

    @Override
    public CreditCard getCard() {
        return model.getCard(cardId);
    }

    @Override
    public void updatePayStart(Long id, int dayOfMonth) {
        model.updatePayStart(id, dayOfMonth);
    }

    @Override
    public void updatePayEnd(Long id, int dayOfMonth) {
        model.updatePayEnd(id, dayOfMonth);
    }
}
