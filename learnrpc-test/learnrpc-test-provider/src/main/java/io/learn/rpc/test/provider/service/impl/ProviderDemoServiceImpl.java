package io.learn.rpc.test.provider.service.impl;

import io.learn.rpc.annotation.RpcService;
import io.learn.rpc.test.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.provider.service.impl
 * @className: ProviderDemoServiceImpl
 * @author: ycd20
 * @description: service impl
 * @date: 2022/10/30 11:00
 * @version: 1.0
 */
@RpcService(interfaceClass = DemoService.class,
        interfaceClassName = "io.learn.rpc.test.scanner.service.DemoService",
        version = "1.0.0",
        group = "learn")
public class ProviderDemoServiceImpl implements DemoService {
    private final Logger log = LoggerFactory.getLogger(ProviderDemoServiceImpl.class);

    @Override
    public String hello(String name) {
        log.info("hello method parameter===>>>{}", name);
        return "hello" + name;
    }
}
