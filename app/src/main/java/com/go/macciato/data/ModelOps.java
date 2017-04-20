package com.go.macciato.data;

import com.go.macciato.core.RequiredPresenterOps;
import com.go.macciato.model.Card;

import java.util.List;

/**
 * Created by MAV1GA on 20/04/2017.
 */

public interface ModelOps {

    void onConfigurationChanged(RequiredPresenterOps presenter);

    /* Card operations */

    void addCard(Card card);
    List<Card> getAllCards();
    List<Card> getPeriodCards();
    void dismissCard(Card card);
    void payCard(Card card);

}
