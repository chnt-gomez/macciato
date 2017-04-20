package com.go.macciato.core;

/**
 * Created by MAV1GA on 20/04/2017.
 */

public interface RequiredPresenterOps {

    void onOperationError(int stringRes);

    void onOperationSuccessful(Long operationId);
}
