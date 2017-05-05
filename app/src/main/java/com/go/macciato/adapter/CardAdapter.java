package com.go.macciato.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.go.macciato.R;
import com.go.macciato.core.OnRecyclerViewItemClick;
import com.go.macciato.model.CreditCard;
import com.go.macciato.utils.CFormat;
import java.util.List;

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
        holder.setProgress(getProgress(card.getPayStart(), card.getPayEnd()));
        holder.setCardName(card.getCardName());
        holder.setDebt(card.getCurrentDebt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(itemId, holder.getAdapterPosition());}
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void mark(int adapterPosition) {
        items.remove(adapterPosition);
    }

    class CardViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;
        private TextView txtCardDebt, txtCardName;

        CardViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_remaining);
            txtCardDebt = (TextView) itemView.findViewById(R.id.txt_card_debt);
            txtCardName = (TextView) itemView.findViewById(R.id.txt_credit_card_name);
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

    }

    private static int getProgress(int payDay, int deadline){
        return 30;
    }

    private static int getMaxProgress(int payDay, int deadLine){
        return 100;
    }
}
