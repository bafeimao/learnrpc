package io.learn.rpc.proxy.jdk;

import io.learn.rpc.proxy.api.consumer.Consumer;
import io.learn.rpc.proxy.api.object.ObjectProxy;

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
@SuppressWarnings("unchecked")
public class JdkProxyFactory<T> {
    /**
     * service version
     */
    private String serviceVersion;

    /**
     * service group
     */
    private String serviceGroup;

    /**
     * timeout 15s
     */
    private long timeout = 15000;

    /**
     * service consumer
     */
    private Consumer consumer;
    /**
     * serialize type
     */
    private String serializationType;

    /**
     * is async
     */
    private boolean async;

    /**
     * is oneway
     */
    private boolean oneway;

    public JdkProxyFactory(String serviceVersion, String serviceGroup, String serializationType, long timeout,
                           Consumer consumer,
                           boolean async, boolean oneway) {
        this.serviceVersion = serviceVersion;
        this.serviceGroup = serviceGroup;
        this.timeout = timeout;
        this.consumer = consumer;
        this.serializationType = serializationType;
        this.async = async;
        this.oneway = oneway;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                new ObjectProxy<>(clazz, serviceVersion, serviceGroup, serializationType,
                        timeout, consumer, async, oneway)
        );
    }
}
