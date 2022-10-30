package io.learn.rpc.protocol.base;

import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.base
 * @className: RpcMessage
 * @author: ycd20
 * @description: rpcMessage
 * @date: 2022/10/30 11:24
 * @version: 1.0
 */
public class RpcMessage implements Serializable {
    /**
     * is oneway
     */
    private boolean oneway;
    /**
     * is async
     */
    private boolean async;

    public boolean getOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public boolean getAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }
}
