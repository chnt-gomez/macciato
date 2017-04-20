package com.go.macciato.model;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.joda.time.DateTime;

/**
 * Created by MAV1GA on 17/04/2017.
 */

public class Card extends SugarRecord{

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

    public int getTintColor() {
        return tintColor;
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
    }

    private int status;

    @Ignore
    public static final int DISABLED = 0;

    @Ignore
    public static final int ENABLED = 1;

    public Card (String cardName){
        this.cardName = cardName;
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
