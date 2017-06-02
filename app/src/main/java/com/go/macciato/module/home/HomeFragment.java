package com.go.macciato.module.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.go.macciato.R;
import com.go.macciato.adapter.CardAdapter;
import com.go.macciato.core.BaseFragment;
import com.go.macciato.core.OnRecyclerViewItemClick;
import com.go.macciato.dialogs.NewCreditCardDialog;
import com.go.macciato.model.CreditCard;
import com.go.macciato.module.config.CardConfigActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeFragment extends BaseFragment implements HomeViewRequiredOps, OnRecyclerViewItemClick{

    @BindView(R.id.cards_list)
    RecyclerView mRecyclerView;

    private CardAdapter adapter;
    private HomePresenterRequiredOps homePresenter;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_LAYOUT, R.layout.fragment_home);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        super.init();


        mRecyclerView = (RecyclerView)findView(R.id.cards_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        //Divider decoration
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        mRecyclerView.addItemDecoration(horizontalDecoration);

        //Swipe listener
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.mark(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                showSnackBar("Done!");
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
        onReload();
    }

    @Override
    public void onLoading() {
        adapter = new CardAdapter( homePresenter.getAllCards(), this);
    }

    @Override
    public void onDoneLoading() {
        super.onDoneLoading();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homePresenter = (HomePresenterRequiredOps)context;
    }

    @OnClick (R.id.btn_add)
    public void onBtnAddClick(){

        NewCreditCardDialog.build(getContext(), R.string.new_credit_card, -1,
                new NewCreditCardDialog.RequiredNewCreditCardOps() {
            @Override
            public void onNewCreditCard(CreditCard card) {
                homePresenter.addCard(card);
            }
        }).show();
    }

    @Override
    protected void onReload() {
        Loader cardLoader = new Loader(this);
        cardLoader.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onOperationSuccessful(String message) {
        super.onOperationSuccessful(message);
        onReload();
    }

    @Override
    public void onClick(long itemId, int position) {
        Intent intent = new Intent(getContext(), CardConfigActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("card_id", itemId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
