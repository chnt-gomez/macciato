package com.go.macciato.module.home;

import com.go.macciato.R;
import com.go.macciato.core.BaseActivity;
import com.go.macciato.model.Card;

import java.util.List;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeActivity extends BaseActivity implements HomePresenterRequiredOps{

    @Override
    protected void init() {
        super.init();
        addFragment(HomeFragment.newInstance(), false);
    }

    @Override
    public List<Card> getAllCards() {
        return null;
    }

    @Override
    public List<Card> getActiveCards() {
        return null;
    }

    @Override
    public List<Card> getPeriodCards() {
        return null;
    }

    @Override
    public Card getCard(long cardId) {
        return null;
    }

    @Override
    public Card updateCard(Card card) {
        return null;
    }

    @Override
    public void payCard(long cardId) {

    }

    @Override
    public void addCard(Card newCard) {
        model.addCard(newCard);
    }
}
