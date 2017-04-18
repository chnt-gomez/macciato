package com.go.macciato.model;


import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 17/04/2017.
 */

public class Card{

    private String cardName;
    private float annualCost;
    private int payStart;
    private int payEnd;
    private float totalDebt;
    private float currentDebt;



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

    public float getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(float totalDebt) {
        this.totalDebt = totalDebt;
    }

    public float getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(float curenDebt) {
        this.currentDebt = curenDebt;
    }

    public String getCurrentDebtMask(){
        return String.valueOf(currentDebt);
    }
}
