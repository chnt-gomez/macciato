package com.go.macciato.data;

import com.go.macciato.R;
import com.go.macciato.core.RequiredPresenterOps;
import com.go.macciato.model.CreditCard;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

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
            card.setStatus(CreditCard.RUNNING);
            card.save();
            presenter.onOperationSuccessful(card.getId());
        }else{
            presenter.onOperationError(R.string.generic_error);
        }
    }

    private boolean validateCard(CreditCard card) {
        //Check duplicate name
        return CreditCard.find(CreditCard.class, "card_name = ?", card.getCardName()).size() < 1;
    }

    @Override
    public List<CreditCard> getAllCards() {
        return CreditCard.listAll(CreditCard.class);

    }

    @Override
    public void dismissCard(CreditCard card) {

    }

    @Override
    public void payCard(CreditCard card) {

    }

    @Override
    public CreditCard getCard(long cardId) {
        return CreditCard.findById(CreditCard.class, cardId);
    }

    @Override
    public void updatePayStart(Long id, int dayOfMonth) {
        CreditCard card = CreditCard.findById(CreditCard.class, id);
        card.setPayStart(dayOfMonth);
        card.save();
        presenter.onOperationSuccessful(-1l);
    }

    @Override
    public void updatePayEnd(Long id, int dayOfMonth) {
        CreditCard card = CreditCard.findById(CreditCard.class, id);
        card.setPayEnd(dayOfMonth);
        card.save();
        presenter.onOperationSuccessful(-1l);
    }

    @Override
    public void dismissCard(long cardId) {
        CreditCard card = CreditCard.findById(CreditCard.class, cardId);
        card.setStatus(CreditCard.PAID);
    }

    @Override
    public List<CreditCard> getActiveCards() {
        List<CreditCard> cards = getAllCards();
        List<CreditCard> activeCards = new ArrayList<>();
        //First, activate the cards that are paid


        int today = DateTime.now().getDayOfMonth();

        for (CreditCard c : cards){
            // If today is after the payday, the the card must be activated
            if (c.getPayEnd() < today && c.getStatus() == CreditCard.PAID){
                c.setStatus(CreditCard.RUNNING);
                c.save();
            }
            // If today is after the cut day, the card must be shown
            if (c.getStatus() == CreditCard.RUNNING && c.getPayStart() <= today){
                activeCards.add(c);
            }

            //Add the delayed cards
            if (c.getStatus() == CreditCard.RUNNING && today > c.getPayEnd()){
                c.setStatus(CreditCard.DELAYED);
                activeCards.add(c);
                c.save();
            }

        }

        //Now let's just return all running cards within the date of payment
        return activeCards;
    }
}
