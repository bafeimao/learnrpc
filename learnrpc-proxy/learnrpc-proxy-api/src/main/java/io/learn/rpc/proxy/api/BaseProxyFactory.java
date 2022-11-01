package io.learn.rpc.proxy.api;

import io.learn.rpc.proxy.api.config.ProxyConfig;
import io.learn.rpc.proxy.api.object.ObjectProxy;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.api
 * @className: BaseProxyFactory
 * @author: ycd20
 * @description: base proxy factory
 * @date: 2022/11/1 22:22
 * @version: 1.0
 */
public abstract class BaseProxyFactory<T> implements ProxyFactory {
    protected ObjectProxy<T> objectProxy;


    @Override
    public <T> void init(ProxyConfig<T> proxyConfig) {
        this.objectProxy = new ObjectProxy(proxyConfig.getClazz(),
                proxyConfig.getServiceGroup(),
                proxyConfig.getServiceVersion(),
                proxyConfig.getSerializationType(),
                proxyConfig.getTimeout(),
                proxyConfig.getConsumer(),
                proxyConfig.getAsync(),
                proxyConfig.getOneway());
    }
}
