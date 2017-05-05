package com.go.macciato.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by MAV1GA on 05/05/2017.
 */

public class CFormat {

    public static String formatAsCurrency(float arg){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setMinimumFractionDigits(2);
        format.setCurrency(Currency.getInstance("USD"));
        return format.format(arg);
    }

}
