package io.learn.rpc.loadbalancer.random;

import io.learn.rpc.loadbalancer.api.ServiceLoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.loadbalancer.random
 * @className: RandomServiceLoadBalancer
 * @author: ycd20
 * @description: random service load balancer
 * @date: 2022/11/20 10:08
 * @version: 1.0
 */
public class RandomServiceLoadBalancer<T> implements ServiceLoadBalancer<T> {

    private final Logger log = LoggerFactory.getLogger(RandomServiceLoadBalancer.class);

    @Override
    public T select(List<T> servers, int hashCode) {
        log.info("基于随机算法的负载均衡策略");
        if (servers == null || servers.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(servers.size());
        return servers.get(index);
    }
}
