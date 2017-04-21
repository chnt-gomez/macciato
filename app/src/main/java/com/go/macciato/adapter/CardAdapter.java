package com.go.macciato.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.go.macciato.R;
import com.go.macciato.model.CreditCard;

import java.util.List;

/**
 * Created by MAV1GA on 17/04/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CreditCard> items;

    public CardAdapter (List<CreditCard> items){
        this.items = items;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_row, parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CreditCard card = items.get(position);
        //holder.setMaxProgress(card.getMaxProgress());
        //holder.setProgress(card.getCurrentProgress());
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
        private TextView txtCardDebt;

        CardViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_remaining);
            txtCardDebt = (TextView) itemView.findViewById(R.id.txt_card_debt);
        }

        void setProgress(int progress){
            progressBar.setProgress(progress);
        }
        void setMaxProgress(int progress) {
            progressBar.setMax(progress);
        }
        void setDebt(String debt){
            txtCardDebt.setText(debt);
        }
    }
}
