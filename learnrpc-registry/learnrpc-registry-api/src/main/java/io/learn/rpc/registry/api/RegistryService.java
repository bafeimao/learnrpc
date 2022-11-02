package io.learn.rpc.registry.api;

import io.learn.rpc.protocol.meta.ServiceMeta;
import io.learn.rpc.registry.api.config.RegistryConfig;

import java.io.IOException;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.registry.api.config
 * @className: RegistryService
 * @author: ycd20
 * @description: registry service
 * @date: 2022/11/1 22:56
 * @version: 1.0
 */
public interface RegistryService {

    /**
     * service registry
     *
     * @param serviceMeta
     * @throws Exception
     */
    void register(ServiceMeta serviceMeta) throws Exception;

    /**
     * service unRegistry
     *
     * @param serviceMeta
     * @throws Exception
     */
    void unRegister(ServiceMeta serviceMeta) throws Exception;

    /**
     * service discovery
     *
     * @param serviceName
     * @param invokerHashCode
     * @return
     * @throws Exception
     */
    ServiceMeta discovery(String serviceName, int invokerHashCode) throws Exception;

    /**
     * service destroy
     *
     * @throws IOException
     */
    void destroy() throws IOException;

    /**
     * default init method
     *
     * @param registryConfig
     * @throws Exception
     */
    default void init(RegistryConfig registryConfig) throws Exception {
    }
}
