package com.go.macciato.module.home;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.go.macciato.R;
import com.go.macciato.core.BaseActivity;
import com.go.macciato.model.CreditCard;

import java.util.List;

import butterknife.BindView;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeActivity extends BaseActivity implements HomePresenterRequiredOps{

    @BindView(R.id.main_appbar)
    Toolbar toolbar;

    @Override
    protected void init() {
        super.init();

        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.home_menu);
        addFragment(HomeFragment.newInstance(), false);
    }



    @Override
    public List<CreditCard> getAllCards() {
        return model.getAllCards();
    }

    @Override
    public List<CreditCard> getActiveCards() {
        return null;
    }

    @Override
    public List<CreditCard> getPeriodCards() {
        return null;
    }

    @Override
    public CreditCard getCard(long cardId) {
        return null;
    }

    @Override
    public CreditCard updateCard(CreditCard card) {
        return null;
    }

    @Override
    public void payCard(long cardId) {

    }

    @Override
    public void addCard(CreditCard newCard) {
        model.addCard(newCard);
    }

}
