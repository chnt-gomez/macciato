package com.go.macciato.module.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.go.macciato.R;
import com.go.macciato.adapter.CardAdapter;
import com.go.macciato.core.BaseFragment;
import com.go.macciato.model.Card;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindBitmap;
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


        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homePresenter = (HomePresenterRequiredOps)context;
    }

    @OnClick (R.id.btn_add)
    public void onAddClick(){
        Card card = new Card("Card test");
        Log.d("Card created: ", card.getCardName());
        homePresenter.addCard(card);

    }
}
