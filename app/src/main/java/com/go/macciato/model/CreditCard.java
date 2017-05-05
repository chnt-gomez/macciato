package com.go.macciato.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 21/04/2017.
 */

public class CreditCard extends SugarRecord {

    public CreditCard(){super();}

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public float getAnnualCost() {
        return annualCost;
    }

    public void setAnnualCost(float annualCost) {
        this.annualCost = annualCost;
    }

    public int getPayStart() {
        return payStart;
    }

    public void setPayStart(int payStart) {
        this.payStart = payStart;
    }

    public int getPayEnd() {
        return payEnd;
    }

    public void setPayEnd(int payEnd) {
        this.payEnd = payEnd;
    }

    public int getTintColor() {
        return tintColor;
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
    }

    private String cardName;
    private float annualCost;
    private int payStart;
    private int payEnd;
    private int tintColor;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(float currentDebt) {
        this.currentDebt = currentDebt;
    }

    private int status;
    private float currentDebt;


}
