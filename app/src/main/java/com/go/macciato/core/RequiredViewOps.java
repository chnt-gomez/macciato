package com.go.macciato.core;

/**
 * Created by MAV1GA on 20/04/2017.
 */

interface RequiredViewOps {

    void onOperationSuccessful(String message);
    void onOperationSuccessful(String message, long operationId);
    void onOperationError(String message);

}
