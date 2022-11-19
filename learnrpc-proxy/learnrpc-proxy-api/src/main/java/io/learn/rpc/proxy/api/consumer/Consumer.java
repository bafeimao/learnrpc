package io.learn.rpc.proxy.api.consumer;

import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.proxy.api.future.RpcFuture;
import io.learn.rpc.registry.api.RegistryService;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.api.consumer
 * @className: Consumer
 * @author: ycd20
 * @description: consumer
 * @date: 2022/10/31 7:58
 * @version: 1.0
 */
public interface Consumer {
    /**
     * consumer send request
     *
     * @param protocol
     * @return
     * @throws Exception
     */
    RpcFuture sendRequest(RpcProtocol<RpcRequest> protocol, RegistryService registryService) throws Exception;
}
