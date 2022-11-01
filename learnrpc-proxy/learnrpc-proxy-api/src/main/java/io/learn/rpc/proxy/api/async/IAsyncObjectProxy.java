package io.learn.rpc.proxy.api.async;

import io.learn.rpc.proxy.api.future.RpcFuture;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.api.async
 * @className: IAsyncObjectProxy
 * @author: ycd20
 * @description: iasync
 * @date: 2022/11/1 21:25
 * @version: 1.0
 */
public interface IAsyncObjectProxy {
    /**
     * async call proxy object
     *
     * @param funcName function name
     * @param args     args
     * @return RpcFuture
     */
    RpcFuture call(String funcName, Object... args);
}
