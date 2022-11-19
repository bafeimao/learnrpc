package io.learn.rpc.proxy.api.config;

import io.learn.rpc.proxy.api.consumer.Consumer;
import io.learn.rpc.registry.api.RegistryService;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.proxy.api.config
 * @className: ProxyConfig
 * @author: ycd20
 * @description: proxy config
 * @date: 2022/11/1 22:16
 * @version: 1.0
 */
public class ProxyConfig<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 5678129L;

    /**
     * class instance
     */
    private Class<T> clazz;

    /**
     * service version
     */
    private String serviceVersion;

    /**
     * service group
     */
    private String serviceGroup;

    /**
     * timeout
     */
    private long timeout;

    /**
     * consumer
     */
    private Consumer consumer;

    /**
     * serializeType
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

    private RegistryService registryService;
    public ProxyConfig() {
    }

    public ProxyConfig(Class<T> clazz, String serviceVersion, String serviceGroup, String serializationType,
                       long timeout, RegistryService registryService, Consumer consumer, boolean async,
                       boolean oneway) {
        this.clazz = clazz;
        this.serviceVersion = serviceVersion;
        this.serviceGroup = serviceGroup;
        this.timeout = timeout;
        this.consumer = consumer;
        this.serializationType = serializationType;
        this.async = async;
        this.oneway = oneway;
        this.registryService = registryService;
    }

    public RegistryService getRegistryService() {
        return registryService;
    }

    public void setRegistryService(RegistryService registryService) {
        this.registryService = registryService;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getSerializationType() {
        return serializationType;
    }

    public void setSerializationType(String serializationType) {
        this.serializationType = serializationType;
    }

    public boolean getAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        async = async;
    }

    public boolean getOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }
}
