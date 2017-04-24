package com.go.macciato.module.home;

import com.go.macciato.model.CreditCard;

import java.util.List;

/**
 * Created by MAV1GA on 20/04/2017.
 */

interface HomePresenterRequiredOps {

    List<CreditCard> getAllCards();

    List<CreditCard> getActiveCards();

    List<CreditCard> getPeriodCards();

    CreditCard getCard(long cardId);

    CreditCard updateCard(CreditCard card);

    void payCard(long cardId);

    void addCard(CreditCard newCard);


}
