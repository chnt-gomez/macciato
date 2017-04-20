package com.go.macciato.data;

import com.go.macciato.R;
import com.go.macciato.core.RequiredPresenterOps;
import com.go.macciato.model.Card;

import java.util.List;

/**
 * Created by MAV1GA on 20/04/2017.
 */

public class MacciatoEngine implements ModelOps{

    private static MacciatoEngine instance;
    private static RequiredPresenterOps presenter;

    public static MacciatoEngine getInstance(RequiredPresenterOps presenter){
        if (instance == null){
            instance = new MacciatoEngine();
        }
        instance.setPresenter(presenter);
        return instance;
    }

    private void setPresenter(RequiredPresenterOps presenterOps){
        presenter = presenterOps;
    }

    @Override
    public void onConfigurationChanged(RequiredPresenterOps presenter) {
        instance.setPresenter(presenter);
    }

    @Override
    public void addCard(Card card) {
        if (validateCard(card)){
            card.save();
            presenter.onOperationSuccessful(card.getId());
        }else{
            presenter.onOperationError(R.string.generic_error);
        }
    }

    private boolean validateCard(Card card) {
        return true;
    }

    @Override
    public List<Card> getAllCards() {
        return null;
    }

    @Override
    public List<Card> getPeriodCards() {
        return null;
    }

    @Override
    public void dismissCard(Card card) {

    }

    @Override
    public void payCard(Card card) {

    }
}
