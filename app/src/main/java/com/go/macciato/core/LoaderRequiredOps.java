package com.go.macciato.core;

/**
 * Created by MAV1GA on 24/04/2017.
 */

interface LoaderRequiredOps {
    void onStartLoading();
    void onLoading();
    void onDoneLoading();
    void onErrorLoading();
}
