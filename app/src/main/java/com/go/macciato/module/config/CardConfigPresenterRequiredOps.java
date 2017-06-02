package com.go.macciato.module.config;

import com.go.macciato.model.CreditCard;

/**
 * Created by MAV1GA on 16/05/2017.
 */

public interface CardConfigPresenterRequiredOps {

    CreditCard getCard();


    void updatePayStart(Long id, int dayOfMonth);

    void updatePayEnd(Long id, int dayOfMonth);
}
