package com.go.macciato.data;

import com.go.macciato.core.RequiredPresenterOps;
import com.go.macciato.model.CreditCard;

import java.util.List;

/**
 * Created by MAV1GA on 20/04/2017.
 */

public interface ModelOps {

    void onConfigurationChanged(RequiredPresenterOps presenter);

    /* Card operations */

    void addCard(CreditCard card);
    List<CreditCard> getAllCards();
    List<CreditCard> getPeriodCards();
    void dismissCard(CreditCard card);
    void payCard(CreditCard card);

}
