package com.go.macciato.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import java.util.Locale;

/**
 * Created by MAV1GA on 21/04/2017.
 */

public class CreditCard extends SugarRecord {

    @Ignore
    public static int PAID = 0;

    @Ignore
    public static int RUNNING = 1;

    @Ignore
    public static int INACTIVE = 2;

    @Ignore
    public static int DELAYED = 3;

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
    private float monthDebt;

    public float getMonthDebt() {
        return monthDebt;
    }

    public void setMonthDebt(float monthDebt) {
        this.monthDebt = monthDebt;
    }

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

    public String getMonthDebtMask(){
        return String.format(Locale.getDefault(), "$ %.2f", monthDebt);
    }

    public String getCurrentDebtMask(){
        return String.format(Locale.getDefault(), "$ %.2f", currentDebt);
    }
}
