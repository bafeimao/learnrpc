package io.learn.rpc.consumer;

import io.learn.rpc.common.exception.RegistryException;
import io.learn.rpc.consumer.common.RpcConsumer;
import io.learn.rpc.proxy.api.ProxyFactory;
import io.learn.rpc.proxy.api.async.IAsyncObjectProxy;
import io.learn.rpc.proxy.api.config.ProxyConfig;
import io.learn.rpc.proxy.api.object.ObjectProxy;
import io.learn.rpc.proxy.jdk.JdkProxyFactory;
import io.learn.rpc.registry.api.RegistryService;
import io.learn.rpc.registry.api.config.RegistryConfig;
import io.learn.rpc.registry.zookeeper.ZookeeperRegistryService;
import org.apache.commons.lang3.StringUtils;
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

    private RegistryService registryService;

    public RpcClient(String registryAddress, String registryType,
                     String serviceVersion, String serviceGroup, String serializationType, long timeout,
                     boolean async, boolean oneway) {
        this.serviceVersion = serviceVersion;
        this.serviceGroup = serviceGroup;
        this.serializationType = serializationType;
        this.timeout = timeout;
        this.async = async;
        this.oneway = oneway;
        this.registryService = this.getRegistryService(registryAddress, registryType);

    }

    private RegistryService getRegistryService(String registryAddress, String registryType) {
        if (StringUtils.isEmpty(registryType)) {
            throw new IllegalArgumentException("registry type is null");
        }
        //todo extend SPI
        RegistryService registryService = new ZookeeperRegistryService();
        try {
            registryService.init(new RegistryConfig(registryAddress, registryType));
        } catch (Exception e) {
            log.error("RpcClient init registry service throws exception:{}", e);
            throw new RegistryException(e.getMessage(), e);
        }
        return registryService;
    }

    public <T> T create(Class<T> interfaceClass) {
        ProxyFactory proxyFactory = new JdkProxyFactory<T>();
        proxyFactory.init(new ProxyConfig(interfaceClass, serviceVersion, serviceGroup, serializationType,
                timeout, registryService, RpcConsumer.getInstance(), async, oneway));
        return proxyFactory.getProxy(interfaceClass);
    }

    public <T> IAsyncObjectProxy createAsync(Class<T> interfaceClass) {
        return new ObjectProxy<T>(interfaceClass, serviceVersion, serializationType, serviceGroup, timeout,
                registryService,
                RpcConsumer.getInstance(), async, oneway);
    }

    public void shutdown() {
        RpcConsumer.getInstance().close();
    }
}
