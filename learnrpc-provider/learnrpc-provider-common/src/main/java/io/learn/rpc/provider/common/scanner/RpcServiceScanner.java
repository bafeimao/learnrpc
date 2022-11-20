package io.learn.rpc.provider.common.scanner;

import io.learn.rpc.spi.annotation.RpcService;
import io.learn.rpc.common.helper.RpcServiceHelper;
import io.learn.rpc.common.scanner.ClassScanner;
import io.learn.rpc.protocol.meta.ServiceMeta;
import io.learn.rpc.registry.api.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.scanner.server
 * @className: RpcServiceScanner
 * @author: ycd20
 * @description: @RpcService annotation scan
 * @date: 2022/10/29 20:29
 * @version: 1.0
 */
public class RpcServiceScanner extends ClassScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServiceScanner.class);

    public static Map<String, Object> doScannerWithRpcServiceAnnotationFilterAndRegistryService(
            String host, int port, String scanPackage, RegistryService registryService
    ) throws Exception {
        Map<String, Object> handlerMap = new HashMap<>();
        List<String> classNameList = getClassNameList(scanPackage);
        if (classNameList == null || classNameList.isEmpty()) {
            return handlerMap;
        }
        classNameList.stream().forEach((className) -> {
            try {
                Class<?> clazz = Class.forName(className);
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                if (rpcService != null) {
                    //优先使用interfaceClass, interfaceClass的name为空，再使用interfaceClassName
                    ServiceMeta serviceMeta = new ServiceMeta(getServiceName(rpcService), rpcService.version(),
                            rpcService.group(), host, port);
                    //将元数据注册到注册中心
                    registryService.register(serviceMeta);
                    handlerMap.put(RpcServiceHelper.buildServiceKey(serviceMeta.getServiceName(),
                            serviceMeta.getServiceVersion(), serviceMeta.getServiceGroup()), clazz.newInstance());
                }
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception: {}", e);
            }
        });
        return handlerMap;

    }

    /**
     * get Service name
     *
     * @param rpcService
     * @return
     */
    private static String getServiceName(RpcService rpcService) {
        //priority use interface class
        Class clazz = rpcService.interfaceClass();
        if (clazz == void.class) {
            return rpcService.interfaceClassName();
        }
        String serviceName = clazz.getName();
        if (serviceName == null || serviceName.trim().isEmpty()) {
            serviceName = rpcService.interfaceClassName();
        }
        return serviceName;
    }


}
