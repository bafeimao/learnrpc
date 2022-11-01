package io.learn.rpc.consumer;

import io.learn.rpc.consumer.common.RpcConsumer;
import io.learn.rpc.proxy.api.async.IAsyncObjectProxy;
import io.learn.rpc.proxy.api.object.ObjectProxy;
import io.learn.rpc.proxy.jdk.JdkProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer
 * @className: RpcClient
 * @author: ycd20
 * @description: rpc client
 * @date: 2022/10/31 22:06
 * @version: 1.0
 */
public class RpcClient {
    private final Logger log = LoggerFactory.getLogger(RpcClient.class);

    /**
     * service version
     */
    private String serviceVersion;
    /**
     * service group
     */
    private String serviceGroup;
    /**
     * serializationType
     */
    private String serializationType;
    /**
     * timeout
     */
    private long timeout;
    /**
     * is async
     */
    private boolean async;
    /**
     * is oneway
     */
    private boolean oneway;

    public RpcClient(String serviceVersion, String serviceGroup, String serializationType, long timeout,
                     boolean async, boolean oneway) {
        this.serviceVersion = serviceVersion;
        this.serviceGroup = serviceGroup;
        this.serializationType = serializationType;
        this.timeout = timeout;
        this.async = async;
        this.oneway = oneway;
    }

    public <T> T create(Class<T> interfaceClass) {
        JdkProxyFactory<T> jdkProxyFactory = new JdkProxyFactory<T>(
                serviceVersion, serviceGroup, serializationType, timeout, RpcConsumer.getInstance(), async, oneway
        );
        return jdkProxyFactory.getProxy(interfaceClass);
    }

    public <T> IAsyncObjectProxy createAsync(Class<T> interfaceClass) {
        return new ObjectProxy<T>(interfaceClass, serviceVersion, serializationType, serviceGroup, timeout,
                RpcConsumer.getInstance(), async, oneway);
    }

    public void shutdown() {
        RpcConsumer.getInstance().close();
    }
}
