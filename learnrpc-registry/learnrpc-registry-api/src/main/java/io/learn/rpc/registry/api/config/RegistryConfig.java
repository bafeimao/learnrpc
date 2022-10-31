package io.learn.rpc.registry.api.config;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.registry.api.config
 * @className: RegistryConfig
 * @author: ycd20
 * @description: registry config
 * @date: 2022/11/1 22:54
 * @version: 1.0
 */
public class RegistryConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 77665999L;

    /**
     * registry addr
     */
    private String registryAddr;
    /**
     * registry type
     */
    private String registryType;

    public RegistryConfig(String registryAddr, String registryType) {
        this.registryAddr = registryAddr;
        this.registryType = registryType;
    }


    public String getRegistryAddr() {
        return registryAddr;
    }

    public void setRegistryAddr(String registryAddr) {
        this.registryAddr = registryAddr;
    }

    public String getRegistryType() {
        return registryType;
    }

    public void setRegistryType(String registryType) {
        this.registryType = registryType;
    }
}
