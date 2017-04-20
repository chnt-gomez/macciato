package com.go.macciato.module.home;

import com.go.macciato.model.Card;

import java.util.List;

/**
 * Created by MAV1GA on 20/04/2017.
 */

interface HomePresenterRequiredOps {

    List<Card> getAllCards();

    List<Card> getActiveCards();

    List<Card> getPeriodCards();

    Card getCard(long cardId);

    Card updateCard(Card card);

    void payCard(long cardId);

    void addCard(Card newCard);

}
