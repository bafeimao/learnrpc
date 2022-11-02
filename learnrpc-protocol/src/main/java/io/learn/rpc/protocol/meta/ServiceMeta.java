package io.learn.rpc.protocol.meta;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.meta
 * @className: ServiceMeta
 * @author: ycd20
 * @description: service meta
 * @date: 2022/11/1 22:50
 * @version: 1.0
 */
public class ServiceMeta implements Serializable {
    @Serial
    private static final long serialVersionUID = 5678445L;
    /**
     * service name
     */
    private String serviceName;

    /**
     * service Version
     */
    private String serviceVersion;

    /**
     * service addr
     */
    private String serviceAddr;
    /**
     * service port
     */
    private int servicePort;
    /**
     * service group
     */
    private String serviceGroup;

    public ServiceMeta() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public ServiceMeta(String serviceName, String serviceVersion, String serviceAddr, int servicePort,
                       String serviceGroup) {
        this.serviceName = serviceName;
        this.serviceVersion = serviceVersion;
        this.serviceAddr = serviceAddr;
        this.servicePort = servicePort;
        this.serviceGroup = serviceGroup;
    }
}
