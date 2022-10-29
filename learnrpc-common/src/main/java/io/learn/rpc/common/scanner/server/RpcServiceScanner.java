package io.learn.rpc.common.scanner.server;

import io.learn.rpc.annotation.RpcService;
import io.learn.rpc.common.scanner.ClassScanner;
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
            /*String host, int port,*/ String scanPackage/* RegistryService registryService **/
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
                    //Priority use interface class ,interface class name is null then use interface class name
                    //todo subsequent registration with the registry
                    LOGGER.info("marked @RpcService annotation class instance name===>>>"
                            + clazz.getName());
                    LOGGER.info("@RpcService annotation marked attribute information:");
                    LOGGER.info("interfaceClass===>>> " + rpcService.interfaceClass().getName());
                    LOGGER.info("interfaceClassName===>>>" + rpcService.interfaceClassName());
                    LOGGER.info("version===>>>" + rpcService.version());
                    LOGGER.info("group===>>>" + rpcService.group());
                }
            } catch (Exception e) {
                LOGGER.error("scan classes throw exception: {}", e);
            }
        });
        return handlerMap;
    }
}
