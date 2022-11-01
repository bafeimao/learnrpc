package io.learn.rpc.proxy.api;

import io.learn.rpc.proxy.api.config.ProxyConfig;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.api
 * @className: ProxyFactory
 * @author: ycd20
 * @description: proxy factory
 * @date: 2022/11/1 22:20
 * @version: 1.0
 */
public interface ProxyFactory {
    /**
     * get proxy object
     */
    <T> T getProxy(Class<T> clazz);

    /**
     * default initialize
     */
    default <T> void init(ProxyConfig<T> proxyConfig) {
    }
}
