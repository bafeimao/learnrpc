package io.learn.rpc.loadbalancer.api;

import java.util.List;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.loadbalancer.api
 * @className: ServiceLoadBalancer
 * @author: ycd20
 * @description: serverr load balancer
 * @date: 2022/11/20 10:05
 * @version: 1.0
 */
public interface ServiceLoadBalancer<T> {

    /**
     * select server node for loadbalancer
     *
     * @param servers  server list
     * @param hashCode hashcode
     * @return serviceNode
     */
    T select(List<T> servers, int hashCode);
}
