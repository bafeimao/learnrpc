package io.learn.rpc.common.helper;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.helper
 * @className: RpcServiceHelper
 * @author: ycd20
 * @description: rpc service helper
 * @date: 2022/10/30 19:25
 * @version: 1.0
 */
public class RpcServiceHelper {

    /**
     * join string
     *
     * @param serviceName    name
     * @param serviceVersion version
     * @param group          group
     * @return 'serviceName#serviceVersion#serviceGroup'
     */
    public static String buildServiceKey(String serviceName, String serviceVersion, String group) {
        return String.join("#", serviceName, serviceVersion, group);
    }
}
