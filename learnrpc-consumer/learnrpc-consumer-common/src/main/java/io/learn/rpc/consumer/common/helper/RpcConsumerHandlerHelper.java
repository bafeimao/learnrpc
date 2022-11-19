package io.learn.rpc.consumer.common.helper;

import io.learn.rpc.common.threadpool.ClientThreadPool;
import io.learn.rpc.consumer.common.handler.RpcConsumerHandler;
import io.learn.rpc.protocol.meta.ServiceMeta;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.helper
 * @className: RpcConsumerHandlerHelper
 * @author: ycd20
 * @description: rpc Consumer handler helper
 * @date: 2022/11/19 20:35
 * @version: 1.0
 */
public class RpcConsumerHandlerHelper {
    private static Map<String, RpcConsumerHandler> rpcConsumerHandlerMap;

    static {
        rpcConsumerHandlerMap = new ConcurrentHashMap<>();
    }

    private static String getKey(ServiceMeta key) {
        return key.getServiceAddr().concat("_")
                .concat(String.valueOf(key.getServicePort()));
    }

    public static void put(ServiceMeta key, RpcConsumerHandler value) {
        rpcConsumerHandlerMap.put(getKey(key), value);
    }

    public static RpcConsumerHandler get(ServiceMeta key) {
        return rpcConsumerHandlerMap.get(getKey(key));
    }

    public static void closeRpcClientHandler() {
        Collection<RpcConsumerHandler> rpcConsumerHandlers
                = rpcConsumerHandlerMap.values();

        if (rpcConsumerHandlers != null) {
            rpcConsumerHandlers.forEach((RpcConsumerHandler::close));
        }
        rpcConsumerHandlerMap.clear();
    }


}
