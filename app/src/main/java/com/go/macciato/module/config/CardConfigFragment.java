package com.go.macciato.module.config;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.go.macciato.R;
import com.go.macciato.core.BaseFragment;
import com.go.macciato.model.CreditCard;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MAV1GA on 05/05/2017.
 */

public class CardConfigFragment extends BaseFragment{

    @BindView(R.id.edt_card_name)
    EditText edtCardName;

    @BindView(R.id.btn_pay_start)
    Button btnPayStart;

    @BindView(R.id.btn_pay_end)
    Button btnPayEnd;

    @BindView(R.id.txt_total_debt)
    TextView txtTotalDebt;

    @BindView(R.id.txt_month_due)
    TextView txtMonthDebt;

    private CardConfigPresenterRequiredOps cardConfigPresenter;

    public static CardConfigFragment newInstance(){
        CardConfigFragment fragment = new CardConfigFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_LAYOUT, R.layout.fragment_card_config);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.cardConfigPresenter = (CardConfigPresenterRequiredOps) context;
    }

    @OnClick (R.id.btn_pay_start)
    public void onClickBtnPayStart(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cardConfigPresenter.updatePayStart(cardConfigPresenter.getCard().getId(), dayOfMonth);
                btnPayStart.setText(String.valueOf(dayOfMonth));
            }
        }, year, month, day).show();
    }

    @OnClick (R.id.btn_pay_end)
    public void onClickBtnPayEnd(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cardConfigPresenter.updatePayEnd(cardConfigPresenter.getCard().getId(), dayOfMonth);
                btnPayEnd.setText(String.valueOf(dayOfMonth));
            }
        }, year, month, day).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    protected void init() {
        super.init();
        Loader loader = new Loader(this);
        loader.execute();
    }

    @Override
    public void onLoading() {
        super.onLoading();
        CreditCard card = cardConfigPresenter.getCard();
        edtCardName.setText(card.getCardName());


        btnPayStart.setText(card.getPayStart() == 0 ? "-" : String.valueOf(card.getPayStart()));
        btnPayEnd.setText(card.getPayEnd() == 0 ? "-" : String.valueOf(card.getPayEnd()));

        txtMonthDebt.setText(card.getMonthDebtMask());
        txtTotalDebt.setText(card.getCurrentDebtMask());

    }

}
