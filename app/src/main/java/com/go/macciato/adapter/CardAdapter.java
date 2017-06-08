package com.go.macciato.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.go.macciato.R;
import com.go.macciato.core.OnRecyclerViewItemClick;
import com.go.macciato.model.CreditCard;
import com.go.macciato.utils.CFormat;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;

import java.util.List;
import java.util.Locale;

/**
 * Created by MAV1GA on 17/04/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CreditCard> items;
    private OnRecyclerViewItemClick callback;

    public CardAdapter (List<CreditCard> items, OnRecyclerViewItemClick callback){
        this.items = items;
        this.callback = callback;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card_item, parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        CreditCard card = items.get(position);
        final long itemId = card.getId();
        holder.setMaxProgress(getMaxProgress(card.getPayStart(), card.getPayEnd()));
        holder.setProgress(getProgress(card.getPayEnd()));
        holder.setCardName(card.getCardName());
        holder.setDebt(card.getCurrentDebt());
        holder.setDaysToPay(getFormattedDaysToPay(getProgress(card.getPayEnd())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(itemId, holder.getAdapterPosition());}
        });
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }else{
            return 0;
        }
    }

    public void mark(int adapterPosition) {
        items.remove(adapterPosition);
    }

    class CardViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;
        private TextView txtCardDebt, txtCardName, txtDaysRemaining;

        CardViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_remaining);
            txtCardDebt = (TextView) itemView.findViewById(R.id.txt_card_debt);
            txtCardName = (TextView) itemView.findViewById(R.id.txt_credit_card_name);
            txtDaysRemaining = (TextView) itemView.findViewById(R.id.txt_remaining_days);
        }

        void setProgress(int progress){
            progressBar.setProgress(progress);
        }
        void setMaxProgress(int progress) {
            progressBar.setMax(progress);
        }
        void setDebt(float debt){
            txtCardDebt.setText(CFormat.formatAsCurrency(debt));
        }
        void setCardName(String cardName){
            txtCardName.setText(cardName);
        }
        void setDaysToPay(String formattedDaysToPay){txtDaysRemaining.setText(formattedDaysToPay);}

    }

    private static int getProgress(int deadline){
        Log.d("TODAY IS:", String.valueOf(deadline));
        try {
            int today = DateTime.now().getDayOfMonth();
            if (deadline >= today) {
                return deadline - today;
            } else {
                final DateTime time = new DateTime();
                final DateTime timeToPay = new DateTime().withDate(DateTime.now().getYear(), DateTime.now().getMonthOfYear() + 1,
                        deadline);
                return Days.daysBetween(time.withTimeAtStartOfDay(), timeToPay.withTimeAtStartOfDay()).getDays() + 1;
            }
        }catch (IllegalFieldValueException e){
            return 1;
        }

    }

    private static int getMaxProgress(int payDay, int deadline){
        try {
            if (payDay < deadline) {
                return deadline - payDay;
            } else if (payDay == deadline) {
                return 1;
            } else {
                final DateTime time = new DateTime().withDate(DateTime.now().getYear(), DateTime.now().getMonthOfYear(), payDay);
                final DateTime timeToPay = new DateTime().withDate(DateTime.now().getYear(), DateTime.now().getMonthOfYear() + 1,
                        deadline);
                return Days.daysBetween(time.withTimeAtStartOfDay(), timeToPay.withTimeAtStartOfDay()).getDays() + 1;
            }
        }catch(IllegalFieldValueException e){
            Log.e("ERROR", e.getMessage());
            return 1;
        }
    }

    private String getFormattedDaysToPay(int daysToPay){
        if (daysToPay == 0){
            return "Pay today";
        }
        if (daysToPay > 1){
            return String.format(Locale.getDefault(), "%d dias restantes", daysToPay);
        }
        if (daysToPay < 0){
            return "Pago retrasado";
        }
        return "-";
    }
}
