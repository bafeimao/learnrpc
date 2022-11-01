package io.learn.rpc.proxy.jdk;

import io.learn.rpc.proxy.api.BaseProxyFactory;
import io.learn.rpc.proxy.api.ProxyFactory;
import io.learn.rpc.proxy.api.consumer.Consumer;

import java.lang.reflect.Proxy;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.jdk
 * @className: JdkProxyFactory
 * @author: ycd20
 * @description: jdk proxy factory
 * @date: 2022/10/31 21:26
 * @version: 1.0
 */
public class JdkProxyFactory<T> extends BaseProxyFactory<T> implements ProxyFactory {

    @Override
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                objectProxy
        );
    }
}
