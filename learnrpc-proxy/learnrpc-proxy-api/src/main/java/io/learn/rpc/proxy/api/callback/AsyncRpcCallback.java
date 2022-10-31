package io.learn.rpc.proxy.api.callback;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.callback
 * @className: AsyncRpcCallback
 * @author: ycd20
 * @description: callback
 * @date: 2022/10/30 23:10
 * @version: 1.0
 */
public interface AsyncRpcCallback {
    /**
     * success
     */
    void onSuccess(Object result);

    /**
     * exception callback
     */
    void onException(Exception e);
}
