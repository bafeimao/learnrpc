package io.learn.rpc.consumer.common.context;


import io.learn.rpc.proxy.api.future.RpcFuture;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.context
 * @className: RpcContext
 * @author: ycd20
 * @description: rpc context
 * @date: 2022/10/30 22:52
 * @version: 1.0
 */
public class RpcContext {

    public RpcContext() {
    }

    /**
     * rpc context instance
     */
    private static final RpcContext AGENT = new RpcContext();

    /**
     * InheritableThreadLocal save RpcFuture
     */
    private static final InheritableThreadLocal<RpcFuture>
            RPC_FUTURE_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    /**
     * get context
     *
     * @return rpc service context
     */
    public static RpcContext getContext() {
        return AGENT;
    }

    /**
     * put RpcFuture in threadLocal
     *
     * @param rpcFuture
     */

    public void setRpcFuture(RpcFuture rpcFuture) {
        RPC_FUTURE_INHERITABLE_THREAD_LOCAL.set(rpcFuture);
    }

    /**
     * get rpcFuture
     *
     * @return
     */
    public RpcFuture getRpcFuture() {
        return RPC_FUTURE_INHERITABLE_THREAD_LOCAL.get();
    }

    /**
     * remove rpcFuture
     */
    public void removeRpcFuture() {
        RPC_FUTURE_INHERITABLE_THREAD_LOCAL.remove();
    }

}
