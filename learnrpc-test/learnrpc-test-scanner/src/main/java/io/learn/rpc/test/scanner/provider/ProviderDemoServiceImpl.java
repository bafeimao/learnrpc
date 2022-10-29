package io.learn.rpc.test.scanner.provider;

import io.learn.rpc.annotation.RpcService;
import io.learn.rpc.test.scanner.service.DemoService;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.scanner.provider
 * @className: ProviderDemoServiceImpl
 * @author: ycd20
 * @description: DemoService impl
 * @date: 2022/10/29 21:35
 * @version: 1.0
 */
@RpcService(interfaceClass = DemoService.class, interfaceClassName =
        "io.learn.rpc.test.scanner.service.DemoService", version = "1.0.0", group = "learn")
public class ProviderDemoServiceImpl implements DemoService {

}
