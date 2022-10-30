package io.learn.rpc.protocol.response;

import io.learn.rpc.protocol.base.RpcMessage;

import java.io.Serial;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.response
 * @className: RpcResponse
 * @author: ycd20
 * @description: rpcResponse
 * @date: 2022/10/30 11:30
 * @version: 1.0
 */
public class RpcResponse extends RpcMessage {
    @Serial
    private static final long serialVersionUID = 425335067890L;
    private String error;
    private Object result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
