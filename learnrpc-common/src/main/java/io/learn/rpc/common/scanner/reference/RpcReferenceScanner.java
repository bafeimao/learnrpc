package io.learn.rpc.common.scanner.reference;

import io.learn.rpc.spi.annotation.RpcReference;
import io.learn.rpc.common.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.scanner.reference
 * @className: RpcReferenceScanner
 * @author: ycd20
 * @description: @RpcReference scanner
 * @date: 2022/10/29 21:11
 * @version: 1.0
 */
public class RpcReferenceScanner extends ClassScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcReferenceScanner.class);

    /**
     * scan class from specifies package and filter use @RpcService annotation
     *
     * @param host
     * @param port
     * @param scanPackage
     * @param registryService
     * @return
     */
    public static Map<String, Object>
    doScannerWithRpcReferenceAnnotationFilter(/*String host, int port,*/ String scanPackage/**,RegistryService
     registryService**/) throws Exception {
        Map<String, Object> handlerMap = new HashMap<>();
        List<String> classNameList = getClassNameList(scanPackage);
        if (classNameList == null || classNameList.isEmpty()) {
            return handlerMap;
        }
        classNameList.stream().forEach((classname) -> {
            try {
                Class<?> clazz = Class.forName(classname);
                Field[] declaredFields = clazz.getDeclaredFields();
                Stream.of(declaredFields).forEach((field -> {
                    RpcReference rpcReference = field.getAnnotation(RpcReference.class);
                    if (rpcReference != null) {
                        //todo todo subsequent registration with the registry
                        LOGGER.info("marked @RpcReferenceService annotation class instance name===>>>"
                                + field.getName());
                        LOGGER.info("@RpcReferenceService annotation marked attribute information:");
                        LOGGER.info("version===>>>" + rpcReference.version());
                        LOGGER.info("group===>>>" + rpcReference.group());
                        LOGGER.info("registryType===>>>" + rpcReference.registryType());
                        LOGGER.info("registryAddress===>>>" + rpcReference.registryAddress());
                    }
                }));
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception: {}", e);
            }
        });
        return handlerMap;
    }
}
