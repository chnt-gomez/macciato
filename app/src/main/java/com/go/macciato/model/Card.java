package com.go.macciato.model;


import android.util.Log;

import com.orm.SugarRecord;

import org.joda.time.DateTime;
import org.joda.time.Days;

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

    public Card (float debt, int payDay, int endPayday){
        this.totalDebt = debt;
        this.payStart = payDay;
        this.payEnd = endPayday;
    }


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

    public long getPayStart() {
        return payStart;
    }

    public void setPayStart(int payStart) {
        this.payStart = payStart;
    }

    public long getPayEnd() {
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
        return String.valueOf(totalDebt);
    }

    public int getCurrentProgress() {
        int days = DateTime.now().dayOfMonth().get();
        Log.d("Today is: ", ""+days);
        Log.d("Last Day is: ", ""+payEnd);
        Log.d("Progress should be: ", String.valueOf(days-payStart));
        return days - payStart;
    }

    public int getMaxProgress(){
        int n = payEnd - payStart;
        Log.d("Max should be: ", ""+n);
        return n;
    }

}
