package io.learn.rpc.provider;

import io.learn.rpc.provider.common.scanner.RpcServiceScanner;
import io.learn.rpc.provider.common.service.base.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.provider.common
 * @className: RpcSingleServer
 * @author: ycd20
 * @description: RpcSingle Server
 * @date: 2022/10/30 10:33
 * @version: 1.0
 */
public class RpcSingleServer extends BaseServer {

    private final Logger log = LoggerFactory.getLogger(RpcSingleServer.class);

    public RpcSingleServer(String serverAddress, String registryAddress, String registryType, String scanPackage,
                           String reflectType) {
        //
        super(serverAddress, registryAddress, registryType, reflectType);
        try {
            this.handlerMap =
                    RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService(
                            this.host, this.port, scanPackage, registryService);
        } catch (Exception e) {
            log.error("RPC Server init error", e);
        }
    }
}
