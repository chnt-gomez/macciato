package com.go.macciato.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.go.macciato.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 07/04/2017.
 */

public class BaseFragment extends Fragment implements RequiredViewOps, LoaderRequiredOps, Toolbar.OnMenuItemClickListener{

    protected static final String FRAGMENT_LAYOUT = "fragment_layout";
    protected View fragmentView;
    protected static ProgressDialog progressInstance;
    protected PresenterOps presenter;
    protected View findView(int resId){
        return fragmentView.findViewById(resId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(getArguments().getInt(FRAGMENT_LAYOUT), null);
        initButterKnife();
        return fragmentView;
    }

    protected void onReload(){
    }

    protected void init(){

    }

    protected void initButterKnife(){
        if (fragmentView != null){
            ButterKnife.bind(this, fragmentView);
        }
    }

    protected void showSnackBar(String message){
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setViewLayer(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = (PresenterOps)context;
    }

    @Override
    public void onOperationSuccessful(String message) {
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOperationSuccessful(String message, long operationId) {
        Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOperationError(String message) {
        Snackbar snack = Snackbar.make(fragmentView, message, Snackbar.LENGTH_SHORT);
        TextView tv = (TextView)snack.getView().findViewById((android.support.design.R.id.snackbar_text));
        tv.setTextColor(Color.parseColor("#ff7f7f"));
        snack.show();
    }

    protected static void buildLoadingDialog(Context context, @Nullable String message){
        progressInstance = new ProgressDialog(context);
        progressInstance.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (message != null){
            progressInstance.setMessage(message);
        }else{
            progressInstance.setMessage("Loading...");
        }
        progressInstance.setIndeterminate(true);
        progressInstance.setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    protected class Loader extends AsyncTask<String, Void, Void> {

        private LoaderRequiredOps callback;

        public Loader (LoaderRequiredOps callback){
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.onStartLoading();
        }

        @Override
        protected Void doInBackground(String... params) {
            callback.onLoading();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onDoneLoading();
            callback = null;
        }
    }

    @Override
    public void onStartLoading() {
        buildLoadingDialog(getContext(), null);
        progressInstance.show();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onDoneLoading() {
        if (progressInstance != null && progressInstance.isShowing()){
            progressInstance.dismiss();
        }
    }

    @Override
    public void onErrorLoading() {
        if (progressInstance != null && progressInstance.isShowing()){
            progressInstance.dismiss();
        }

    }
}
