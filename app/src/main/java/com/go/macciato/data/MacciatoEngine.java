package com.go.macciato.data;

import com.go.macciato.core.RequiredPresenterOps;
import com.go.macciato.model.CreditCard;

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

    private MacciatoEngine(){}

    private void setPresenter(RequiredPresenterOps presenterOps){
        presenter = presenterOps;
    }

    @Override
    public void onConfigurationChanged(RequiredPresenterOps presenter) {
        instance.setPresenter(presenter);
    }

    @Override
    public void addCard(CreditCard card) {
        if (validateCard(card)) {

            card.save();
            presenter.onOperationSuccessful(card.getId());
        }

    }

    private boolean validateCard(CreditCard card) {

        //Check for duplicate name
        return CreditCard.find(CreditCard.class, "card_name = ?", card.getCardName()).size() < 1;
    }

    @Override
    public List<CreditCard> getAllCards() {
        //return Card.listAll(Card.class);
        return null;
    }

    @Override
    public List<CreditCard> getPeriodCards() {
        return null;
    }

    @Override
    public void dismissCard(CreditCard card) {

    }

    @Override
    public void payCard(CreditCard card) {

    }
}
