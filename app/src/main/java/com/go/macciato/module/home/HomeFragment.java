package com.go.macciato.module.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import com.go.macciato.R;
import com.go.macciato.adapter.CardAdapter;
import com.go.macciato.core.BaseFragment;
import com.go.macciato.model.Card;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class HomeFragment extends BaseFragment implements HomeViewRequiredOps{

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

        List<Card> cards = homePresenter.getAllCards();
        for (Card c : cards){
            Log.d("Card: ", c.getCardName());
        }

        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homePresenter = (HomePresenterRequiredOps)context;
    }

    @OnClick (R.id.btn_add)
    public void onBtnAddClick(){

        //TODO: Add CArd dialog builder.
        homePresenter.addCard(new Card("test"));
    }
}
